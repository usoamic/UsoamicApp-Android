package io.usoamic.wallet.custom.listview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import io.usoamic.wallet.R
import io.usoamic.wallet.custom.base.BaseFrameLayout
import io.usoamic.wallet.custom.listview.model.ListItem
import io.usoamic.wallet.databinding.ViewListBinding
import io.usoamic.wallet.databinding.ViewListItemBinding
import io.usoamic.wallet.extensions.invisible
import io.usoamic.wallet.custom.listview.model.ListItemCallback

class ListView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseFrameLayout(context, attrs, defStyleAttr, R.layout.view_list) {
    override val binding: ViewListBinding by lazy { ViewListBinding.bind(requireView()) }
    private lateinit var onItemClickCallback: ListItemCallback

    private val list = mutableListOf<ListItem>()

    val size get() =  list.size
    val lastIndex get() =  list.lastIndex

    fun addAll(items: List<ListItem>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(callback: ListItemCallback) {
        onItemClickCallback = callback
    }

    private fun notifyDataSetChanged() {
        list.forEachIndexed { i, item ->
            val view = View.inflate(context, R.layout.view_list_item, null)

            ViewListItemBinding.bind(view).apply {
                tvTitle.text = item.title
                tvSubtitle.text = item.subtitle
                if(i == lastIndex) {
                    vDivider.invisible()
                }
                clContainer.setOnClickListener {
                    onItemClickCallback.invoke(i, item)
                }
            }

            binding.llContainer.addView(view)
        }
    }
}