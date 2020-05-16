package io.usoamic.wallet.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(open val view: View) : RecyclerView.ViewHolder(view) {
    protected abstract fun bind(item: T)
}