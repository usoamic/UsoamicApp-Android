package io.usoamic.wallet.extensions

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import io.usoamic.wallet.R
import io.usoamic.wallet.domain.models.NavDirections

const val ARGS = "args"

fun NavController.navigateTo(args: NavDirections) {
    when(args) {
        is NavDirections.Add -> navigate(R.id.addFragment, bundleOf(ARGS to args))
    }
}