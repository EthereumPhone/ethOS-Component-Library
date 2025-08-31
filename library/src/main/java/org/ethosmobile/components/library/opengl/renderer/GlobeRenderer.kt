package org.ethosmobile.contacts.opengl.renderer

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.util.Log
import androidx.compose.ui.unit.Dp
import org.ethosmobile.contacts.opengl.OpenGLColor
import org.ethosmobile.contacts.opengl.hexToRGBA
import org.ethosmobile.contacts.opengl.normalizeColor
import org.ethosmobile.contacts.opengl.shapes.Globe
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GlobeRenderer : GLSurfaceView.Renderer {

    // Compile shaders and link the program
    private val vertexShaderCode = """
            uniform mat4 uMVPMatrix;
            attribute vec4 vPosition;
            void main() {
                gl_Position = uMVPMatrix * vPosition;
            }
        """.trimIndent()

    private val fragmentShaderCode = """
            precision mediump float;
            uniform vec4 uColor;
            void main() {
                gl_FragColor = uColor;
            }
        """.trimIndent()

    private lateinit var globe: Globe
    private val vertexBuffer: FloatBuffer
    private val indexBuffer: ShortBuffer

    // Shader variables
    private var program: Int = 0
    private var positionHandle: Int = 0
    private var mvpMatrixHandle: Int = 0
    private var colorHandle: Int = 0 // Handle for uColor uniform
    private var aspectRatio: Float = 1f


    // Rotation state
    var angleY: Float = 0f
    private val axialTiltDegrees: Float = 23.5f // Earth's axial tilt

    // Base spin speed (degrees per frame). Keep gentle but a bit faster.
    private var rotationSpeedY: Float = 0.55f
    private var rotationDirectionSign: Float = 1f
    var isAnimating: Boolean = true // Control animation state

    // Raw velocity requested by UI thread
    var scrollVelocity: Float = 0.0f
        set(value) {
            field = value
        }
    // Smoothing and clamping for scroll influence
    private var smoothedScrollVelocity: Float = 0.0f
    private val maxScrollVelocity: Float = 0.75f
    private val smoothingFactor: Float = 0.15f
    private var scrollMultiplier = 1.8f
    private var scrollVelocityDecreaseFactor = 0.03f

    var linewidth: Float = 5.0f


    val colorRGBA = hexToRGBA("#4E83DB")
    var renderColor: OpenGLColor = OpenGLColor(colorRGBA[0],colorRGBA[1],colorRGBA[2])


    init {
        // Allocate buffers for vertices and indices
        globe = Globe()
        vertexBuffer = ByteBuffer.allocateDirect(globe.vertices.size * 4)
            .order(ByteOrder.nativeOrder()).asFloatBuffer().apply {
                put(globe.vertices)
                position(0)
            }

        indexBuffer = ByteBuffer.allocateDirect(globe.indices.size * 2)
            .order(ByteOrder.nativeOrder()).asShortBuffer().apply {
                put(globe.indices)
                position(0)
            }
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // Set up OpenGL environment (opaque surface for better performance)
        GLES20.glClearColor(5 / 255f, 5 / 255f, 5 / 255f, 1.0f)
        GLES20.glEnable(GLES20.GL_DEPTH_TEST)

        // For an opaque background, blending is not needed for our current wireframe lines
        // (kept disabled to reduce GPU work)

        val vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

        program = GLES20.glCreateProgram().also {
            GLES20.glAttachShader(it, vertexShader)
            GLES20.glAttachShader(it, fragmentShader)
            GLES20.glLinkProgram(it)
        }

        // Check for linking errors
        val linkStatus = IntArray(1)
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0)
        if (linkStatus[0] == 0) {
            Log.e("GlobeRenderer", "Error linking program: ${GLES20.glGetProgramInfoLog(program)}")
            GLES20.glDeleteProgram(program)
            program = 0
        }

        // Bind program once and cache handles
        GLES20.glUseProgram(program)
        positionHandle = GLES20.glGetAttribLocation(program, "vPosition")
        mvpMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix")
        colorHandle = GLES20.glGetUniformLocation(program, "uColor")

        // Delete shaders after linking
        GLES20.glDeleteShader(vertexShader)
        GLES20.glDeleteShader(fragmentShader)
    }

    // Reuse matrices across frames to avoid per-frame allocations
    private val mvpMatrix = FloatArray(16)
    private val rotationMatrixY = FloatArray(16)
    private val tiltMatrix = FloatArray(16)
    private val invTiltMatrix = FloatArray(16)
    private val tempMatrix1 = FloatArray(16)

    override fun onDrawFrame(gl: GL10?) {
        // Clear the screen
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        // Ensure program is bound (defensive)
        GLES20.glUseProgram(program)

        // Enable the vertex attribute array and set the pointer to the vertex buffer
        GLES20.glEnableVertexAttribArray(positionHandle)
        GLES20.glVertexAttribPointer(
            positionHandle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer
        )



        // Set up the Model-View-Projection matrix (MVP)

        // Update rotation speeds based on scroll velocity
        // Smooth and clamp the incoming scroll velocity (allow negative to control direction)
        val clampedIncoming = scrollVelocity.coerceIn(-maxScrollVelocity, maxScrollVelocity)

        // If direction flips, apply incoming instantly to avoid a stall at 0
        if (kotlin.math.abs(clampedIncoming) > 0.0001f) {
            val prev = smoothedScrollVelocity
            val prevSign = if (prev == 0f) 0f else kotlin.math.sign(prev)
            val incomingSign = kotlin.math.sign(clampedIncoming)
            smoothedScrollVelocity = if (prevSign != 0f && prevSign != incomingSign) {
                // Instant direction flip
                clampedIncoming
            } else {
                // Normal smoothing toward target
                prev + (clampedIncoming - prev) * smoothingFactor
            }
        } else {
            // When input is near zero, gently decay smoothed velocity
            smoothedScrollVelocity *= (1f - scrollVelocityDecreaseFactor)
            if (kotlin.math.abs(smoothedScrollVelocity) < 1e-4f) smoothedScrollVelocity = 0f
        }

        // Only apply scroll influence when actively scrolling
        val isActivelyScrolling = kotlin.math.abs(clampedIncoming) > 0.0001f
        val scrollFactor = if (isActivelyScrolling) {
            smoothedScrollVelocity * scrollMultiplier
        } else 0f

        // Latch the base spin direction to the last active scroll direction
        if (isActivelyScrolling) {
            rotationDirectionSign = kotlin.math.sign(clampedIncoming)
            if (rotationDirectionSign == 0f) rotationDirectionSign = 1f
        }

        if (isAnimating){
            // If user is actively scrolling (non-trivial scrollFactor), follow their direction strictly.
            // Otherwise, use the gentle base rotation.
            val step = if (kotlin.math.abs(scrollFactor) > 0.0001f) scrollFactor else rotationSpeedY * rotationDirectionSign
            angleY += step

            // Keep angle within 0..360 to avoid float overflow over long sessions
            if (angleY > 360f) angleY -= 360f
        }

        // Set the identity matrix as the base
        Matrix.setIdentityM(mvpMatrix, 0)

        // Build transform for rotation about a tilted axis:
        // MVP = TiltZ(tilt) * RotY(angle) * TiltZ(-tilt)
        Matrix.setRotateM(rotationMatrixY, 0, angleY, 0.0f, 1.0f, 0.0f)
        Matrix.setRotateM(tiltMatrix, 0, axialTiltDegrees, 0.0f, 0.0f, 1.0f)
        Matrix.setRotateM(invTiltMatrix, 0, -axialTiltDegrees, 0.0f, 0.0f, 1.0f)

        // temp1 = RotY * InvTilt
        Matrix.multiplyMM(tempMatrix1, 0, rotationMatrixY, 0, invTiltMatrix, 0)
        // mvp = Tilt * temp1
        Matrix.multiplyMM(mvpMatrix, 0, tiltMatrix, 0, tempMatrix1, 0)

        // Pass the final MVP matrix to the shader
        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0)



        // Set the line width (e.g., to 5.0f)
        GLES20.glLineWidth(linewidth)

        //colors
        GLES20.glUniform4f(colorHandle, renderColor.r, renderColor.g, renderColor.b, 1f)

        // Draw the globe using the indices buffer
        GLES20.glDrawElements(
            GLES20.GL_LINES,
            globe.indices.size,
            GLES20.GL_UNSIGNED_SHORT,
            indexBuffer
        )

        GLES20.glDisableVertexAttribArray(positionHandle)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        // Set the viewport to the new window dimensions
        GLES20.glViewport(0, 0, width, height)
        aspectRatio = if (height == 0) 1f else width.toFloat() / height.toFloat()
    }

    private fun loadShader(type: Int, shaderCode: String): Int {
        return GLES20.glCreateShader(type).also { shader ->
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)

            // Check compile status
            val compileStatus = IntArray(1)
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0)
            if (compileStatus[0] == 0) {
                Log.e("GlobeRenderer", "Error compiling shader: ${GLES20.glGetShaderInfoLog(shader)}")
                GLES20.glDeleteShader(shader)
            }
        }
    }

}


