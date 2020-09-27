package io.usoamic.wallet.extensions

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import io.usoamic.wallet.domain.models.AppArguments

const val ARGS = "args"
fun NavController.navigate(@IdRes navResId: Int, args: AppArguments? = null) {
    args?.let {
        navigate(navResId, bundleOf(ARGS to it))
    } ?: run {
        navigate(navResId)
    }
}