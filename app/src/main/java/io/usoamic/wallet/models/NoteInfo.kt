package io.usoamic.wallet.models

import io.usoamic.usoamickt.enumcls.NoteType
import org.threeten.bp.LocalDateTime

data class NoteInfo(
    val refId: Long,
    val type: NoteType,
    val content: String,
    val author: String,
    val date: LocalDateTime,
    val isOwn: Boolean
)