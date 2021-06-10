package io.usoamic.app.domain.mappers.note

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.usoamickt.enumcls.NoteType
import io.usoamic.app.domain.models.realm.NoteItemRealm

fun NoteItemRealm.toEntity(): NoteEntity = NoteEntity(
    noteId = id!!.toBigInteger(),
    noteType = NoteType.valueOf(noteType!!),
    noteRefId = refId!!.toBigInteger(),
    content = content!!,
    author = author!!,
    timestamp = timestamp!!.toBigInteger(),
    isAuthor = isAuthor!!
)

fun NoteItem.toType(): NoteType {
    return when(this) {
        is NoteItem.Public -> NoteType.PUBLIC
        is NoteItem.Unlisted -> NoteType.UNLISTED
    }
}