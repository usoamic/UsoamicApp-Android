package io.usoamic.app.ui.main.notes.viewnotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.app.BuildConfig
import io.usoamic.app.R
import io.usoamic.app.custom.adapter.BaseRecyclerAdapter
import io.usoamic.app.custom.adapter.BaseViewHolder
import io.usoamic.app.databinding.ItemOwnNoteBinding
import io.usoamic.app.domain.mappers.note.toType
import io.usoamic.app.extensions.getDrawable
import io.usoamic.app.extensions.toLocalDateTime
import io.usoamic.app.models.notes.ShowNoteInfo
import org.threeten.bp.format.DateTimeFormatter

class NotesAdapter(
    private val onNoteClick: (ShowNoteInfo) -> Unit
) : BaseRecyclerAdapter<NoteItem, NotesAdapter.ViewHolder>() {
    private val dateFormatter = DateTimeFormatter.ofPattern(BuildConfig.DATE_FORMAT)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_own_note, parent, false)
        )
    }

    inner class ViewHolder(override val view: View) : BaseViewHolder<NoteItem>(view) {
        override fun bind(item: NoteItem) = with(ItemOwnNoteBinding.bind(view)) {
            val date = item.timestamp.toLocalDateTime()
            val id = item.id
            val shortInfo = ShowNoteInfo(
                id = id,
                noteType = item.toType()
            )

            ivIcon.setImageDrawable(
                getDrawable(
                    when (item) {
                        is NoteItem.Public -> R.drawable.ic_public_note
                        is NoteItem.Unlisted -> R.drawable.ic_unlisted_note
                    }
                )
            )
            tvTitle.text = item.content
            tvPublicId.text = id.toString()
            tvDate.text = dateFormatter.format(date)

            view.setOnClickListener {
                onNoteClick.invoke(shortInfo)
            }

            bottom.isVisible = (adapterPosition == lastItem)
        }
    }
}