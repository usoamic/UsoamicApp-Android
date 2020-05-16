package io.usoamic.wallet.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, T> : RecyclerView.Adapter<VH>() {
    protected val items = mutableListOf<T>()

    fun addAll(data: List<T>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}