package org.ethosmobile.components.library.opengl.view


import android.content.Context
import android.opengl.GLSurfaceView
import org.ethosmobile.contacts.opengl.OpenGLColor
import org.ethosmobile.contacts.opengl.hexToRGBA
import org.ethosmobile.contacts.opengl.renderer.GlobeRenderer

class OpenGLGlobeView(context: Context) : GLSurfaceView(context) {

    val renderer: GlobeRenderer

    init {
        setEGLContextClientVersion(2)
        // Opaque surface for better performance
        setEGLConfigChooser(8, 8, 8, 0, 16, 0)
        preserveEGLContextOnPause = true

        renderer = GlobeRenderer()
        setRenderer(renderer)

        renderMode = RENDERMODE_CONTINUOUSLY
    }

    fun updateScrollVelocity(velocity: Float) {
        renderer.scrollVelocity = velocity
    }

    fun setColor(hexCode: String) {
        val color = hexToRGBA(hexCode)
        renderer.renderColor = OpenGLColor(color[0],color[1],color[2])
    }

    fun setAnimating(isAnimating: Boolean) {
        renderer.isAnimating = isAnimating
        renderMode = if (isAnimating) RENDERMODE_CONTINUOUSLY else RENDERMODE_WHEN_DIRTY
    }

    fun onResumeView() {
        try { onResume() } catch (_: Exception) {}
    }

    fun onPauseView() {
        try { onPause() } catch (_: Exception) {}
    }
}