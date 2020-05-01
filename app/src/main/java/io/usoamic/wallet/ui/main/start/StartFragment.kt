package io.usoamic.wallet.ui.main.start

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentStartBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.domain.models.NavDirections
import io.usoamic.wallet.extensions.navigateTo
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class StartFragment : BaseViewModelFragment(R.layout.fragment_start) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<StartViewModel>
    override val viewModel: StartViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentStartBinding

    override fun inject() {
        UsoamicWallet.component.inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldHasAccount, ::goToNextFragment)
    }

    private fun goToNextFragment(hasAccount: Boolean) {
        val direction = if(hasAccount) {
            NavDirections.Wallet
        }
        else {
            NavDirections.Auth
        }
        navigator.navigateTo(direction)
    }

    override fun initBinding() {
        binding = FragmentStartBinding.bind(requireView())
    }
}