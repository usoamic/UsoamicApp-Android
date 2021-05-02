package io.usoamic.wallet.models.notes

import io.usoamic.usoamickt.enumcls.NoteType

data class ShowNoteInfo(
    val id: Long,
    val noteType: NoteType = NoteType.PUBLIC
)