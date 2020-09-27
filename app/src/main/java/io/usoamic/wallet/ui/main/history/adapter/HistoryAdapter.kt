package io.usoamic.wallet.ui.main.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import io.usoamic.wallet.R
import io.usoamic.wallet.custom.adapter.BaseRecyclerAdapter
import io.usoamic.wallet.custom.adapter.BaseViewHolder
import io.usoamic.wallet.databinding.ItemHistoryBinding
import io.usoamic.wallet.domain.models.history.TransactionItem
import io.usoamic.wallet.domain.models.history.TransactionType
import io.usoamic.wallet.extensions.getDrawable
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import io.usoamic.wallet.BuildConfig

class HistoryAdapter : BaseRecyclerAdapter<TransactionItem, HistoryAdapter.ViewHolder>() {
    private val formatter = DateTimeFormatter.ofPattern(BuildConfig.DATE_FORMAT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    inner class ViewHolder(override val view: View) : BaseViewHolder<TransactionItem>(view) {
        override fun bind(item: TransactionItem) {
            ItemHistoryBinding.bind(view).apply {
                val date = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(item.timestamp * 1000L),
                    ZoneId.systemDefault()
                )

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
                tvAmount.text = "${item.value.toBigDecimal().stripTrailingZeros().toPlainString()} USO"
                tvDate.text = formatter.format(date)
                bottom.isVisible = (adapterPosition == lastItem)
            }
        }
    }
}