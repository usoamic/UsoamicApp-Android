package io.usoamic.app.domain.models

import android.os.Parcelable
import io.usoamic.usoamickt.enumcls.NoteType
import kotlinx.parcelize.Parcelize

sealed class AppArguments : Parcelable {
    @Parcelize
    data class Note(
        val id: Long,
        val noteType: NoteType
    ) : AppArguments()
}