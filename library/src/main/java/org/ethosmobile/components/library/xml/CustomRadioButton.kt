package org.ethosmobile.components.library.xml

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import org.ethosmobile.components.library.R

class CustomRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val checkboxSize = 28f
    private val textMargin = 20f
    private var isChecked = false
    var text = ""
        private set
    private var onCheckedChangeListener: ((Boolean) -> Unit)? = null
    private var isButtonEnabled = true
    
    init {
        // Load Space Mono font
        val spaceMono = ResourcesCompat.getFont(context, com.camera.dgenlibrary.R.font.spacemono_bold)
        textPaint.typeface = spaceMono
        textPaint.textSize = 20f
        
        // Parse attributes if any
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomRadioButton,
            0, 0
        ).apply {
            try {
                text = getString(R.styleable.CustomRadioButton_text) ?: ""
                isChecked = getBoolean(R.styleable.CustomRadioButton_checked, false)
            } finally {
                recycle()
            }
        }
        
        setOnClickListener {
            if (isButtonEnabled && !isChecked) {
                isChecked = true
                onCheckedChangeListener?.invoke(true)
                invalidate()
            }
        }
        
        // Update colors on first draw
        updateColors()
    }
    
    private fun updateColors() {
        SystemColorManager.refresh(context)
        textPaint.color = SystemColorManager.primaryColorInt
        invalidate()
    }
    
    fun setText(newText: String) {
        text = newText.uppercase()
        invalidate()
    }
    
    fun setChecked(checked: Boolean) {
        if (isChecked != checked) {
            isChecked = checked
            invalidate()
        }
    }
    
    fun isChecked(): Boolean = isChecked
    
    fun setOnCheckedChangeListener(listener: (Boolean) -> Unit) {
        onCheckedChangeListener = listener
    }
    
    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        isButtonEnabled = enabled
        invalidate()
    }
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val textBounds = Rect()
        textPaint.getTextBounds(text.uppercase(), 0, text.length, textBounds)
        
        val totalWidth = checkboxSize + textMargin + textBounds.width()
        val totalHeight = maxOf(checkboxSize, textBounds.height() + 16f)
        
        setMeasuredDimension(
            resolveSize(totalWidth.toInt(), widthMeasureSpec),
            resolveSize(totalHeight.toInt(), heightMeasureSpec)
        )
    }
    
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        // Update colors before drawing
        updateColors()
        
        val centerY = height / 2f
        
        // Draw checkbox square
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        paint.color = if (isButtonEnabled) SystemColorManager.primaryColorInt else 0x80808080.toInt()
        
        val checkboxTop = centerY - checkboxSize / 2
        val checkboxBottom = centerY + checkboxSize / 2
        
        canvas.drawRect(
            0f,
            checkboxTop,
            checkboxSize,
            checkboxBottom,
            paint
        )
        
        // Fill checkbox if checked
        if (isChecked) {
            paint.style = Paint.Style.FILL
            paint.color = if (isButtonEnabled) SystemColorManager.primaryColorInt else 0x80808080.toInt()
            canvas.drawRect(
                4f,
                checkboxTop + 4f,
                checkboxSize - 4f,
                checkboxBottom - 4f,
                paint
            )
        }
        
        // Draw text - center aligned with checkbox
        val textX = checkboxSize + textMargin
        val textBounds = android.graphics.Rect()
        textPaint.getTextBounds(text.uppercase(), 0, text.length, textBounds)
        val textY = centerY + (textBounds.height() / 2f)
        
        textPaint.color = if (isButtonEnabled) SystemColorManager.primaryColorInt else 0x80808080.toInt()
        canvas.drawText(text.uppercase(), textX, textY, textPaint)
    }
} 