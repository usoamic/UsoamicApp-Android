package io.usoamic.wallet.ui.main.deposit

import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.databinding.FragmentDepositBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class DepositFragment : BaseViewModelFragment(R.layout.fragment_deposit) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DepositViewModel>
    override val viewModel: DepositViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentDepositBinding

    override fun initBinding() {
        binding = FragmentDepositBinding.bind(requireView())
    }
}