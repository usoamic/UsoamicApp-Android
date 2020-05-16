package io.usoamic.wallet.ui.main.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.usoamic.wallet.R
import io.usoamic.wallet.databinding.ItemDashboardBinding
import io.usoamic.wallet.custom.adapter.BaseRecyclerAdapter
import io.usoamic.wallet.custom.adapter.BaseViewHolder
import io.usoamic.wallet.extensions.getDrawable
import io.usoamic.wallet.extensions.getString

class DashboardAdapter : BaseRecyclerAdapter<DashboardItem, DashboardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        )
    }

    inner class ViewHolder(override val view: View) : BaseViewHolder<DashboardItem>(view) {
        override fun bind(item: DashboardItem) {
            ItemDashboardBinding.bind(view).apply {
                val drawableRes: Int
                val titleRes: Int
                when (item.type) {
                    DashboardItemType.EthBalance -> {
                        drawableRes = R.drawable.ic_asterisk
                        titleRes = R.string.title_dashboard_eth_balance
                    }
                    DashboardItemType.UsoBalance -> {
                        drawableRes = R.drawable.ic_modx
                        titleRes = R.string.title_dashboard_uso_balance
                    }
                    DashboardItemType.Height -> {
                        drawableRes = R.drawable.ic_cubes
                        titleRes = R.string.title_dashboard_height
                    }
                    DashboardItemType.Supply -> {
                        drawableRes = R.drawable.ic_sync_alt
                        titleRes = R.string.title_dashboard_supply
                    }
                }
                val drawable = getDrawable(drawableRes)
                ivIcon.setImageDrawable(drawable)
                tvTitle.text = getString(titleRes)
                tvValue.text = item.value
            }
        }
    }
}