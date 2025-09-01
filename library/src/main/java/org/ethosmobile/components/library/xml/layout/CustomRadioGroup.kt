package org.ethosmobile.components.library.xml.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import org.ethosmobile.components.library.xml.CustomRadioButton

class CustomRadioGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val radioButtons = mutableListOf<CustomRadioButton>()
    private var checkedRadioButton: CustomRadioButton? = null
    private var onCheckedChangeListener: ((CustomRadioButton?) -> Unit)? = null

    init {
        orientation = HORIZONTAL
    }
    
    override fun onFinishInflate() {
        super.onFinishInflate()
        
        // Automatically register any CustomRadioButton children that were added via XML
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child is CustomRadioButton) {
                addRadioButton(child)
            }
        }
    }

    fun addRadioButton(radioButton: CustomRadioButton) {
        if (!radioButtons.contains(radioButton)) {
            radioButtons.add(radioButton)
            
            // Only add to view if it's not already a child
            if (radioButton.parent == null) {
                addView(radioButton)
            }
            
            radioButton.setOnCheckedChangeListener { isChecked ->
                if (isChecked) {
                    // Uncheck all other radio buttons
                    radioButtons.forEach { rb ->
                        if (rb != radioButton) {
                            rb.setChecked(false)
                        }
                    }
                    checkedRadioButton = radioButton
                    onCheckedChangeListener?.invoke(radioButton)
                }
            }
        }
    }

    fun setOnCheckedChangeListener(listener: (CustomRadioButton?) -> Unit) {
        onCheckedChangeListener = listener
    }

    fun getCheckedRadioButton(): CustomRadioButton? = checkedRadioButton

    fun checkRadioButton(radioButton: CustomRadioButton) {
        if (radioButtons.contains(radioButton)) {
            radioButtons.forEach { rb ->
                rb.setChecked(rb == radioButton)
            }
            checkedRadioButton = radioButton
            onCheckedChangeListener?.invoke(radioButton)
        }
    }

    fun getRadioButtonByText(text: String): CustomRadioButton? {
        return radioButtons.find { it.text.equals(text, ignoreCase = true) }
    }
} 