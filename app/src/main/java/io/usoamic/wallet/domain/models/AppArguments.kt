package io.usoamic.wallet.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class AppArguments : Parcelable {
    @Parcelize
    data class Add(val privateKey: String) : AppArguments()

    @Parcelize
    object Create : AppArguments()
}