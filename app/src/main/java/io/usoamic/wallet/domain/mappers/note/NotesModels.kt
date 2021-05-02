package io.usoamic.wallet.domain.mappers.note

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.usoamickt.enumcls.NoteType
import io.usoamic.wallet.domain.models.realm.NoteItemRealm

fun NoteItemRealm.toEntity(): NoteEntity = NoteEntity(
    noteId = id.toBigInteger(),
    noteType = NoteType.valueOf(noteType!!),
    noteRefId = refId!!.toBigInteger(),
    content = content!!,
    author = author!!,
    timestamp = timestamp!!.toBigInteger()
)