package io.usoamic.wallet.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showMessage(message: String) {
//    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}

fun Fragment.showMessage(@StringRes resId: Int) = showMessage(string(resId))

fun Fragment.string(@StringRes resId: Int) = getString(resId)