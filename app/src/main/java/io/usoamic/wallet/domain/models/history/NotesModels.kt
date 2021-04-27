package io.usoamic.wallet.domain.models.history

import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.usoamickt.enumcls.NoteVisibility
import io.usoamic.wallet.domain.models.realm.NoteItemRealm

fun NoteItemRealm.toEntity(): NoteEntity = NoteEntity(
    noteId = id.toBigInteger(),
    visibility = NoteVisibility.valueOf(visibility!!),
    noteRefId = refId!!.toBigInteger(),
    content = content!!,
    author = author!!,
    timestamp = timestamp!!.toBigInteger()
)

fun NoteEntity.toRealm(): NoteItemRealm = NoteItemRealm(
    id = noteId.toLong(),
    visibility = visibility.toString(),
    refId = noteRefId.toLong(),
    content = content,
    author = author,
    timestamp = timestamp.toLong()
)