package io.usoamic.wallet.extensions

import androidx.navigation.NavController
import io.usoamic.wallet.R
import io.usoamic.wallet.domain.models.NavDirections

const val ARGS = "args"

fun NavController.navigateTo(args: NavDirections) {
    when(args) {
        is NavDirections.Add -> navigate(R.id.addFragment)
        is NavDirections.Create -> navigate(R.id.createFragment)
        is NavDirections.Auth -> navigate(R.id.authFragment)
        is NavDirections.Wallet -> navigate(R.id.walletFragment)
    }
}