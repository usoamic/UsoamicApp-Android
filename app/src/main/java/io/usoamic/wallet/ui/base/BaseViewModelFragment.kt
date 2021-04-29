package io.usoamic.wallet.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.text.Layout
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.navigation.NavGraphNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import io.usoamic.commons.crossplatform.models.common.base.ErrorArguments
import io.usoamic.validateutilkt.error.*
import io.usoamic.wallet.R
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.showDialogWithMessage
import io.usoamic.wallet.extensions.showToast


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
        val stringId = when (error.throwable) {
            is EmptyAddressError -> R.string.empty_address
            is EmptyAppIdError -> R.string.empty_app_id
            is EmptyCommentError -> R.string.empty_comment
            is EmptyConfirmPasswordError -> R.string.empty_confirm_password
            is EmptyDescriptionError -> R.string.empty_description
            is EmptyMnemonicPhraseError -> R.string.empty_mnemonic_phrase
            is EmptyNoteContentError -> R.string.empty_note_content
            is EmptyPasswordError -> R.string.empty_password
            is EmptyPrivateKeyError -> R.string.empty_private_key
            is EmptyPurchaseIdError -> R.string.empty_purchase_id
            is EmptyValueError -> R.string.empty_value
            is InvalidAddressError -> R.string.invalid_address
            is InvalidIdError -> R.string.invalid_id
            is InvalidMnemonicPhraseError -> R.string.invalid_mnemonic_phrase
            is InvalidPrivateKeyError -> R.string.invalid_private_key
            is InvalidValueError -> R.string.invalid_value
            is PasswordsDoNotMatchError -> R.string.passwords_do_not_match
            is PrivateKeyRequiredError -> R.string.private_key_required
            else -> null
        }
        val message = stringId?.let { getString(stringId) } ?: error.message
        ?: getString(R.string.unknown_error)
        val isFinish = (error is ErrorArguments.Fatal)
        showErrorDialog(message, isFinish)
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