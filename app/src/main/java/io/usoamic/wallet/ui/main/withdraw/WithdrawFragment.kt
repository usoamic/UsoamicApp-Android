package io.usoamic.wallet.ui.main.withdraw

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
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
    override val binding: FragmentWithdrawBinding by viewBinding {
        FragmentWithdrawBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.withdrawSubcomponent.create().inject(this)
    }
}