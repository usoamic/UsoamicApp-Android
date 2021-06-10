package io.usoamic.app.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import io.usoamic.commons.crossplatform.models.common.base.ErrorArguments
import io.usoamic.validateutilkt.error.*
import io.usoamic.app.R
import io.usoamic.app.extensions.observe
import io.usoamic.app.extensions.showDialogWithMessage
import io.usoamic.app.extensions.showToast


abstract class BaseViewModelFragment(
    @LayoutRes private val layoutRes: Int
) : BaseFragment(layoutRes) {
    protected abstract val viewModel: BaseViewModel
    private lateinit var logoutDialog: AlertDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel.onViewCreated()
    }

    protected open fun showError(error: ErrorArguments) {
        val message: String? = when (val t = error.throwable) {
            is EmptyAddressError -> getString(R.string.empty_address)
            is EmptyAppIdError -> getString(R.string.empty_app_id)
            is EmptyCommentError -> getString(R.string.empty_comment)
            is EmptyConfirmPasswordError -> getString(R.string.empty_confirm_password)
            is EmptyDescriptionError -> getString(R.string.empty_description)
            is EmptyMnemonicPhraseError -> getString(R.string.empty_mnemonic_phrase)
            is EmptyNoteContentError -> getString(R.string.empty_note_content)
            is EmptyPasswordError -> getString(R.string.empty_password)
            is EmptyPrivateKeyError -> getString(R.string.empty_private_key)
            is EmptyPurchaseIdError -> getString(R.string.empty_purchase_id)
            is EmptyValueError -> getString(R.string.empty_value)
            is InvalidAddressError -> getString(R.string.invalid_address)
            is InvalidIdError -> {
                val minId = t.minId
                val maxId = t.maxId

                when {
                    minId != null -> {
                        getString(R.string.invalid_id_by_min, minId)
                    }
                    maxId != null -> {
                        getString(R.string.invalid_id_by_max, maxId)
                    }
                    else -> {
                        getString(R.string.invalid_id)
                    }
                }
            }
            is InvalidMnemonicPhraseError -> getString(R.string.invalid_mnemonic_phrase)
            is InvalidPrivateKeyError -> getString(R.string.invalid_private_key)
            is InvalidValueError -> getString(R.string.invalid_value)
            is PasswordsDoNotMatchError -> getString(R.string.passwords_do_not_match)
            is PrivateKeyRequiredError -> getString(R.string.private_key_required)
            else -> null
        }

        showErrorDialog(
            error = message ?: error.message ?: getString(R.string.unknown_error),
            isFinish = (error is ErrorArguments.Fatal)
        )
    }

    protected open fun initObservers() {
        observe(viewModel.ldThrowable, ::showError)
        observe(viewModel.ldError, ::showErrorDialog)
        observe(viewModel.ldProgress, ::showProgress)
        observe(viewModel.leLogout, ::doLogout)
        observe(viewModel.ldLogoutProgress, ::showLogoutProgress)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                showLogoutDialog()
            }
        }
        return false
    }

    private fun doLogout(isRemoved: Boolean) {
        if (!isRemoved) {
            showToast(R.string.remove_wallet_error)
        }
        goToAuth()
    }

    protected fun showLogoutDialog() {
        logoutDialog = showDialogWithMessage(
            title = R.string.app_name,
            message = R.string.logout_message,
            listener = { _, _ ->
                viewModel.onLogoutClick()
            },
            withCancel = true
        )
    }

    private fun goToAuth() {
        navigator.navigate(R.id.authFragment)
    }

    override fun onDestroy() {
        if (::logoutDialog.isInitialized) {
            logoutDialog.dismiss()
        }
        super.onDestroy()
    }
}