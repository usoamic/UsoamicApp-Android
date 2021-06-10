package io.usoamic.app.extensions

import android.os.Bundle
import android.os.Parcelable

fun <T : Parcelable> Bundle.requireParcelable(key: String): T = getParcelable<T>(key)!!