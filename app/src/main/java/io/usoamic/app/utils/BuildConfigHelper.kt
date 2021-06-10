package io.usoamic.app.utils

import io.usoamic.app.BuildConfig

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