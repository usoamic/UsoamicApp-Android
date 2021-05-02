package io.usoamic.wallet.domain.mappers.note

import io.reactivex.functions.Function
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.wallet.domain.models.realm.NoteItemRealm

class NoteEntityMapper(private val isOwnNote: Boolean) : Function<NoteEntity, NoteItemRealm> {
    override fun apply(item: NoteEntity): NoteItemRealm {
        return NoteItemRealm(
            id = item.noteId.toLong(),
            noteType = item.noteType.toString(),
            refId = item.noteRefId.toLong(),
            content = item.content,
            author = item.author,
            timestamp = item.timestamp.toLong(),
            isOwnNote = isOwnNote
        )
    }
}