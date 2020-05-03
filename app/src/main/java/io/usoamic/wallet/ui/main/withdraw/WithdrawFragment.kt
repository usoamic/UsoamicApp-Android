package io.usoamic.wallet.ui.main.withdraw

import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentWithdrawBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class WithdrawFragment : BaseViewModelFragment(R.layout.fragment_withdraw) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<WithdrawViewModel>
    override val viewModel: WithdrawViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentWithdrawBinding

    override fun inject() {
        UsoamicWallet.component.withdrawSubcomponent.create().inject(this)
    }

    override fun initBinding() {
        binding = FragmentWithdrawBinding.bind(requireView())
    }

}