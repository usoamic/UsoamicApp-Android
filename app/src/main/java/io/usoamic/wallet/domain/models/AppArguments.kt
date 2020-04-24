package io.usoamic.wallet.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class AppArguments : Parcelable {
    @Parcelize
    data class Add(val privateKey: String) : AppArguments()
}