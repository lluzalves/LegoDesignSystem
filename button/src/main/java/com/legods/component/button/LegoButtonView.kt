package com.legods.component.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.daniel.core.setTextAndVisibility
import com.legods.component.button.databinding.LegoButtonViewLayoutBinding


@BindingMethods(
    BindingMethod(
        type = LegoButtonView::class,
        attribute = "buttonText",
        method = "setButtonText"
    ),
    BindingMethod(
        type = LegoButtonView::class,
        attribute = "buttonViewBackground",
        method = "setButtonViewBackground"
    ),
    BindingMethod(
        type = LegoButtonView::class,
        attribute = "buttonDisabledState",
        method = "setDisabledState"
    ),
    BindingMethod(
        type = LegoButtonView::class,
        attribute = "buttonIsClickable",
        method = "isClickable"
    ),
    BindingMethod(
        type = LegoButtonView::class,
        attribute = "buttonIsFocusable",
        method = "isFocusable"
    ),
    BindingMethod(
        type = LegoButtonView::class,
        attribute = "buttonRightIcon",
        method = "setRightIcon"
    ),
    BindingMethod(
        type = LegoButtonView::class,
        attribute = "buttonLeftIcon",
        method = "setLeftIcon"
    )
)

class LegoButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    lateinit var binding: LegoButtonViewLayoutBinding

    init {
        handleAttrsAndInflateLayout(attrs, defStyleAttr, defStyleAttr)
        setRippleEffect(true)
    }

    fun handleAttrsAndInflateLayout(
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ): View {

        val inflater = LayoutInflater.from(context)
        binding = LegoButtonViewLayoutBinding.inflate(inflater, this, true)


        if (attrs != null) {
            val styledAttrs =
                context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.LegoButtonView,
                    defStyleAttr,
                    0
                )

            styledAttrs.getDrawable(R.styleable.LegoButtonView_buttonViewBackground)
                ?.let { setButtonViewBackground(it) }
            styledAttrs.getDrawable(R.styleable.LegoButtonView_buttonRightIcon)?.let {
                setRightIcon(it)
            }
            styledAttrs.getDrawable(R.styleable.LegoButtonView_buttonLeftIcon)?.let {
                setLeftIcon(it)
            }
            setButtonText(styledAttrs.getText(R.styleable.LegoButtonView_buttonText))

            setDisableState(
                styledAttrs.getBoolean(
                    R.styleable.LegoButtonView_buttonDisabledState,
                    false
                )
            )

            isClickable(styledAttrs.getBoolean(R.styleable.LegoButtonView_isClickable, true))
            isFocusable(styledAttrs.getBoolean(R.styleable.LegoButtonView_isFocusable, true))

            styledAttrs.recycle()
        }
        return rootView
    }

    fun setButtonViewBackground(drawable: Drawable) {
        background = drawable
    }

    fun isClickable(isClickable: Boolean) {
        this.isClickable = isClickable
    }

    fun isFocusable(isFocusable: Boolean) {
        this.isFocusable = isFocusable
    }

    fun setButtonText(text: CharSequence?) {
        binding.textView.setTextAndVisibility(text)
    }

    fun setLeftIcon(drawable: Drawable?) {
        if (drawable != null) {
            binding.leftIcon.visibility = View.VISIBLE
            binding.leftIcon.setImageDrawable(drawable)
        }
    }

    fun setRightIcon(drawable: Drawable?) {
        if (drawable != null) {
            binding.rightIcon.visibility = View.VISIBLE
            binding.rightIcon.setImageDrawable(drawable)
        }
    }

    fun setDisableState(isStateDisabled: Boolean) {
        when (isStateDisabled) {
            true -> {
                isClickable(false)
                ContextCompat.getDrawable(context, R.drawable.button_state_disabled)?.let {
                    setButtonViewBackground(
                        it
                    )
                }
                binding.textView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.lego_color_white
                    )
                )
            }
            false -> {
                ContextCompat.getDrawable(context, R.drawable.ripple_effect_button)?.let {
                    setButtonViewBackground(
                        it
                    )
                }
                binding.textView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.lego_color_black
                    )
                )
            }
        }
    }


    fun setDescription(text: CharSequence?) {
        binding.root.contentDescription = text
    }

    fun setPrimaryButtonOnClick(onClickListener: OnClickListener?) {
        setRippleEffect(true)
    }

    private fun ConstraintLayout.setRippleEffect(showRippleOnClick: Boolean) {
        foreground = if (showRippleOnClick) {
            val outValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
            ResourcesCompat.getDrawable(resources, outValue.resourceId, context.theme)
        } else {
            null
        }
    }

}
