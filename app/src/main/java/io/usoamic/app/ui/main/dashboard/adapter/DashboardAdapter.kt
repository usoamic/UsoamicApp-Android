package io.usoamic.app.ui.main.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import io.usoamic.commons.crossplatform.extensions.toBeautyString
import io.usoamic.app.R
import io.usoamic.app.custom.adapter.BaseRecyclerAdapter
import io.usoamic.app.custom.adapter.BaseViewHolder
import io.usoamic.app.databinding.ItemDashboardBinding
import io.usoamic.app.extensions.getDrawable
import io.usoamic.app.extensions.getString

class DashboardAdapter : BaseRecyclerAdapter<DashboardItem, DashboardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        )
    }

    inner class ViewHolder(override val view: View) : BaseViewHolder<DashboardItem>(view) {
        override fun bind(item: DashboardItem) = with(ItemDashboardBinding.bind(view)) {
            val drawableRes: Int
            val titleRes: Int
            val value: String

            when (item) {
                is DashboardItem.UsoBalance -> {
                    drawableRes = R.drawable.ic_asterisk
                    titleRes = R.string.title_dashboard_uso_balance
                    value = item.data.toBeautyString()
                }
                is DashboardItem.EthBalance -> {
                    drawableRes = R.drawable.ic_modx
                    titleRes = R.string.title_dashboard_eth_balance
                    value = item.data.toBeautyString()
                }
                is DashboardItem.Height -> {
                    drawableRes = R.drawable.ic_cubes
                    titleRes = R.string.title_dashboard_height
                    value = item.data.toBeautyString()
                }
                is DashboardItem.Supply -> {
                    drawableRes = R.drawable.ic_sync_alt
                    titleRes = R.string.title_dashboard_supply
                    value = item.data.toBeautyString()
                }
            }
            val drawable = getDrawable(drawableRes)
            ivIcon.setImageDrawable(drawable)
            tvTitle.text = getString(titleRes)
            tvValue.text = value
            bottom.isVisible = (adapterPosition == lastItem)
        }
    }
}