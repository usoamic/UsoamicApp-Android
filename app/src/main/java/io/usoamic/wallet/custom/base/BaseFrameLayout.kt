package io.usoamic.wallet.custom.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BaseFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, @LayoutRes private val layoutRes: Int
) : FrameLayout(context, attrs, defStyleAttr) {
    private val view: View by lazy { View.inflate(context, layoutRes, this) }
    protected abstract val binding: ViewBinding
    protected fun requireView(): View = view

    init {
        view
    }
}