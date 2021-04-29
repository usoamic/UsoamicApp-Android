package io.usoamic.wallet.extensions

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.usoamic.wallet.R
import io.usoamic.wallet.custom.adapter.BaseViewHolder

fun Fragment.showMessage(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(@StringRes resId: Int) = showToast(getString(resId))

fun Fragment.showMessage(@StringRes resId: Int) = showMessage(getString(resId))

fun Fragment.copyToClipboard(text: String) {
    val clipboard =
        requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("text", text)
    clipboard.setPrimaryClip(clip)
    showMessage(R.string.copied_to_clipboard)
}

val EditText.value: String get() = text.toString()

fun EditText.clear() = setText("")

fun View.show() {
    isVisible = true
}

fun View.gone() {
    isGone = true
}

fun View.invisible() {
    isInvisible = true
}

fun Fragment.showErrorDialogWithMessage(
    message: String,
    listener: DialogInterface.OnClickListener? = null
): AlertDialog {
    return showDialogWithMessage(R.string.error, message, listener)
}

fun Fragment.showDialogWithMessage(
    @StringRes title: Int,
    message: String,
    listener: DialogInterface.OnClickListener? = null,
    withCancel: Boolean = false
): AlertDialog = showDialogWithMessage(getString(title), message, listener, withCancel)

fun Fragment.showDialogWithMessage(
    @StringRes title: Int,
    @StringRes message: Int,
    listener: DialogInterface.OnClickListener? = null,
    withCancel: Boolean = false
): AlertDialog = showDialogWithMessage(
    getString(title),
    getString(message),
    listener,
    withCancel
)

fun Fragment.showDialogWithMessage(
    title: String,
    message: String,
    listener: DialogInterface.OnClickListener? = null,
    withCancel: Boolean = false
): AlertDialog {
    val builder = AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(
            android.R.string.ok,
            listener
        )
        .setIcon(R.drawable.ic_launcher_foreground)

    if (withCancel) {
        builder.setNegativeButton(getString(android.R.string.cancel)) { dialog: DialogInterface, _: Int ->
            dialog.cancel()
        }
    }

    return builder.show()
}

fun <T> BaseViewHolder<T>.getString(@StringRes stringRes: Int): String = context.getString(stringRes)

fun <T> BaseViewHolder<T>.getDrawable(@DrawableRes drawableRes: Int): Drawable? = ContextCompat.getDrawable(context, drawableRes)

fun MenuInflater.inflateLogout(menu: Menu) {
    inflate(R.menu.logout_menu, menu)
}
