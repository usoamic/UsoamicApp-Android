package io.usoamic.wallet.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class NavDirections : Parcelable {
    @Parcelize
    data class Add(val privateKey: String) : NavDirections()

    @Parcelize
    object Create : NavDirections()
}