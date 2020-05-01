package io.usoamic.wallet.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class NavDirections : Parcelable {
    @Parcelize
    object Add : NavDirections()

    @Parcelize
    object Create : NavDirections()

    @Parcelize
    object Wallet : NavDirections()

    @Parcelize
    object Auth : NavDirections()
}