package io.usoamic.wallet.ui.auth.unlock

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentUnlockWalletBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class UnlockWalletFragment : BaseViewModelFragment(R.layout.fragment_unlock_wallet) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<UnlockWalletViewModel>
    override val viewModel: UnlockWalletViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentUnlockWalletBinding

    override fun inject() {
        UsoamicWallet.component.unlockWalletSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    override fun initBinding() {
        binding = FragmentUnlockWalletBinding.bind(requireView())
    }

    override fun showProgress(isProgress: Boolean) {
        binding.apply {
            pbContainer.progressBar.isVisible = isProgress
            clContainer.isInvisible = isProgress
        }
    }

    override fun initObservers() {
        super.initObservers()
    }

    override fun initListeners() {
        super.initListeners()
//        binding.btnNext.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_createFragment_to_addFragment)
//        )
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
    }
}