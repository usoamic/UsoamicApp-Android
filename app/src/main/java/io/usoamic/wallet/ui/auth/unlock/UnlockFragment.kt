package io.usoamic.wallet.ui.auth.unlock

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentUnlockBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.setVersion
import io.usoamic.wallet.extensions.value
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import io.usoamic.wallet.utils.BuildConfigHelper
import javax.inject.Inject

class UnlockFragment : BaseViewModelFragment(R.layout.fragment_unlock) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<UnlockViewModel>
    override val viewModel: UnlockViewModel by viewModels { viewModelFactory }

    override val binding: FragmentUnlockBinding by viewBinding {
        FragmentUnlockBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.unlockSubcomponent.create().inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.leNext) {
            goToWallet()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVersion()
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        clContainer.isInvisible = isProgress
    }

    override fun initListeners() = with(binding) {
        super.initListeners()
        btnNext.setOnClickListener {
            viewModel.onNextClick(etPassword.value)
        }

        btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun goToWallet() {
        navigator.navigate(R.id.walletFragment)
    }
}