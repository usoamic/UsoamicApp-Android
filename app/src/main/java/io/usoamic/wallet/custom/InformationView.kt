package io.usoamic.wallet.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import io.usoamic.wallet.R
import io.usoamic.wallet.databinding.ViewInformationBinding


class InformationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {
    private val view: View by lazy { View.inflate(context, R.layout.view_information, this) }
    private val binding: ViewInformationBinding by lazy { ViewInformationBinding.bind(view) }

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.InformationView, 0, 0
        )
        binding.tvInfo.text = a.getString(R.styleable.InformationView_informationText)
        a.recycle()
    }
}