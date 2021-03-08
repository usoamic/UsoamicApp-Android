package io.usoamic.wallet.ui.auth.unlock

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentUnlockBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.showDialogWithMessage
import io.usoamic.wallet.extensions.showToast
import io.usoamic.wallet.extensions.value
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class UnlockFragment : BaseViewModelFragment(R.layout.fragment_unlock) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<UnlockViewModel>
    override val viewModel: UnlockViewModel by viewModels { viewModelFactory }

    override val binding: FragmentUnlockBinding by viewBinding {
        FragmentUnlockBinding.bind(it.requireView())
    }

    private lateinit var logoutDialog: AlertDialog

    override fun inject() {
        UsoamicWallet.component.unlockSubcomponent.create().inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.leNext) {
            goToWallet()
        }
        observe(viewModel.leLogout) { isRemoved ->
            if (!isRemoved) {
                showToast(R.string.remove_wallet_error)
            }
            goToAuth()
        }
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        clContainer.isInvisible = isProgress
    }

    override fun initListeners() = with(binding) {
        super.initListeners()
        btnNext.setOnClickListener {
            viewModel.onNextClick(binding.etPassword.value)
        }

        btnLogout.setOnClickListener {
            onLogoutClick()
        }
    }

    private fun onLogoutClick() {
        logoutDialog = showDialogWithMessage(
            title = R.string.app_name,
            message = R.string.logout_message,
            listener = DialogInterface.OnClickListener { _, _ ->
                viewModel.onLogoutClick()
            },
            withCancel = true
        )
    }

    override fun onDestroy() {
        if (::logoutDialog.isInitialized) {
            logoutDialog.dismiss()
        }
        super.onDestroy()
    }

    private fun goToWallet() {
        navigator.navigate(R.id.walletFragment)
    }

    private fun goToAuth() {
        navigator.navigate(R.id.authFragment)
    }
}