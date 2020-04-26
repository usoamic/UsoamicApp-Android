package io.usoamic.wallet.extensions

import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showMessage(message: String) {
//    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}

fun Fragment.showMessage(@StringRes resId: Int) = showMessage(getString(resId))

fun View.show() {
    isVisible = true
}

fun View.gone() {
    isGone = true
}

fun View.invisible() {
    isInvisible = true
}