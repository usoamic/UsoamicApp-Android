package io.usoamic.wallet.ui.start

import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentStartBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class StartFragment : BaseViewModelFragment(R.layout.fragment_start) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<StartViewModel>
    override val viewModel: StartViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentStartBinding

    /*
     * TODO: Maybe move this functionality to SingleActivity?
     */

    override fun inject() {
        UsoamicWallet.component.inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldHasAccount, ::goToNextFragment)
    }

    private fun goToNextFragment(hasAccount: Boolean) {
        val direction = if(hasAccount) {
            R.id.unlockFragment
        }
        else {
            R.id.authFragment
        }
        navigator.navigate(direction)
    }

    override fun initBinding() {
        binding = FragmentStartBinding.bind(requireView())
    }
}