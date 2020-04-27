package io.usoamic.wallet.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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

fun Fragment.copyToClipboard(text: String) {
    val clipboard = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("text", text)
    clipboard.setPrimaryClip(clip)
}

fun View.show() {
    isVisible = true
}

fun View.gone() {
    isGone = true
}

fun View.invisible() {
    isInvisible = true
}