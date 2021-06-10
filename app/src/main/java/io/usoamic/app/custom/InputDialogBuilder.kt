package io.usoamic.app.custom

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import io.usoamic.app.R
import io.usoamic.app.databinding.DialogInputViewBinding
import io.usoamic.app.extensions.value

class InputDialogBuilder(context: Context) {
    private val builder = AlertDialog.Builder(context)
    private val view: View = View.inflate(context, R.layout.dialog_input_view, null)
    private val binding: DialogInputViewBinding = DialogInputViewBinding.bind(view)

    fun setTitle(@StringRes resId: Int) = apply {
        builder.setTitle(resId)
    }

    fun setHint(@StringRes resId: Int) = apply {
        binding.editText.setHint(resId)
    }


    fun setInputType(type: Int) = apply {
        binding.editText.inputType = type
    }

    fun setPositiveButton(@StringRes resId: Int, callback: (input: String) -> Unit) = apply {
        builder.setPositiveButton(resId) { _, _ ->
            callback.invoke(binding.editText.value)
        }
    }

    fun create(): AlertDialog {
        return builder.setView(view)
            .create()

    }
}