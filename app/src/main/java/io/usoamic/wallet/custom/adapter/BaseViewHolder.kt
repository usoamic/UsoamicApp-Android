package io.usoamic.wallet.custom.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(open val view: View) : RecyclerView.ViewHolder(view) {
    val context: Context get() = view.context

    abstract fun bind(item: T)
}