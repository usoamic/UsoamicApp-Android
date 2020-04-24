package io.usoamic.wallet.extensions

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import io.usoamic.wallet.R
import io.usoamic.wallet.domain.models.AppArguments

const val ARGS = "args"

fun NavController.navigateTo(args: AppArguments) {
    when(args) {
        is AppArguments.Add -> navigate(R.id.addFragment, bundleOf(ARGS to args))
    }
}