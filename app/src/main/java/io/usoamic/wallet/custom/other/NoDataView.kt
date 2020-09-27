package io.usoamic.wallet.custom.other

import android.content.Context
import android.util.AttributeSet
import io.usoamic.wallet.R
import io.usoamic.wallet.custom.base.BaseFrameLayout
import io.usoamic.wallet.databinding.ViewNoDataBinding

class NoDataView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseFrameLayout(context, attrs, defStyleAttr, R.layout.view_no_data) {
    override val binding: ViewNoDataBinding by lazy { ViewNoDataBinding.bind(requireView()) }

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.NoDataView, 0, 0
        )
        val title = a.getString(R.styleable.NoDataView_no_data_title)
        if (title != null) {
            setTitle(title)
        }
        a.recycle()
    }

    fun setTitle(title: String) {
        binding.tvInfo.text = title
    }
}