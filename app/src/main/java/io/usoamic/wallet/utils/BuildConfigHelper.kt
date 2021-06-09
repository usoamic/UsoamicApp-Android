package io.usoamic.wallet.utils

import io.usoamic.wallet.BuildConfig

object BuildConfigHelper {
    @JvmStatic
    val FULL_VERSION: String
        get() = StringBuilder()
            .append(BuildConfig.VERSION_NAME)
            .append(" (")
            .append(BuildConfig.VERSION_CODE)
            .append(") - ")
            .append(BuildConfig.FLAVOR)
            .toString()
}