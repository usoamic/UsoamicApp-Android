package io.usoamic.wallet.ui.main.notes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.usoamickt.enumcls.NoteType
import io.usoamic.wallet.BuildConfig
import io.usoamic.wallet.R
import io.usoamic.wallet.custom.adapter.BaseRecyclerAdapter
import io.usoamic.wallet.custom.adapter.BaseViewHolder
import io.usoamic.wallet.databinding.ItemOwnNoteBinding
import io.usoamic.wallet.extensions.getDrawable
import io.usoamic.wallet.extensions.toLocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class NotesAdapter : BaseRecyclerAdapter<NoteItem, NotesAdapter.ViewHolder>() {
    private val dateFormatter = DateTimeFormatter.ofPattern(BuildConfig.DATE_FORMAT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    inner class ViewHolder(override val view: View) : BaseViewHolder<NoteItem>(view) {
        override fun bind(item: NoteItem) = with(ItemOwnNoteBinding.bind(view)) {
            val date = item.timestamp.toLocalDateTime()

            ivIcon.setImageDrawable(
                getDrawable(
                    when (item.type) {
                        NoteType.PUBLIC -> R.drawable.ic_public_note
                        NoteType.UNLISTED -> R.drawable.ic_unlisted_note
                    }
                )
            )
            tvTitle.text = item.content
            tvPublicId.text = item.refId.toString()
            tvDate.text = dateFormatter.format(date)

            bottom.isVisible = (adapterPosition == lastItem)
        }
    }
}