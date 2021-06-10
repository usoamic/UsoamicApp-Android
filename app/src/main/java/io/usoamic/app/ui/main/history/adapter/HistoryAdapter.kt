package io.usoamic.app.ui.main.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import io.usoamic.commons.crossplatform.extensions.toBeautyString
import io.usoamic.commons.crossplatform.models.usecases.history.TransactionItem
import io.usoamic.app.BuildConfig
import io.usoamic.app.R
import io.usoamic.app.custom.adapter.BaseRecyclerAdapter
import io.usoamic.app.custom.adapter.BaseViewHolder
import io.usoamic.app.databinding.ItemHistoryBinding
import io.usoamic.app.extensions.getDrawable
import io.usoamic.app.extensions.toLocalDateTime
import io.usoamic.commons.crossplatform.models.usecases.history.TransactionType
import org.threeten.bp.format.DateTimeFormatter

class HistoryAdapter : BaseRecyclerAdapter<TransactionItem, HistoryAdapter.ViewHolder>() {
    private val dateFormatter = DateTimeFormatter.ofPattern(BuildConfig.DATE_FORMAT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    inner class ViewHolder(override val view: View) : BaseViewHolder<TransactionItem>(view) {
        override fun bind(item: TransactionItem) = with(ItemHistoryBinding.bind(view)) {
            val date = item.timestamp.toLocalDateTime()

            val drawableRes: Int
            val address: String

            when (item.type) {
                TransactionType.DEPOSIT -> {
                    drawableRes = R.drawable.ic_deposit
                    address = item.from
                }
                TransactionType.WITHDRAW -> {
                    drawableRes = R.drawable.ic_withdraw
                    address = item.to
                }
            }


            ivIcon.setImageDrawable(getDrawable(drawableRes))
            tvAddress.text = address
            tvAmount.text = item.value.toBeautyString(BuildConfig.TICKER)

            tvDate.text = dateFormatter.format(date)
            bottom.isVisible = (adapterPosition == lastItem)
        }
    }

}