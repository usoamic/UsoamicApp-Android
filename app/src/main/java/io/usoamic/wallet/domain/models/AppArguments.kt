package io.usoamic.wallet.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class AppArguments : Parcelable {
    @Parcelize
    data class Note(
        val refId: Long
    ) : AppArguments()
}