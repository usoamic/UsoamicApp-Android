package io.usoamic.wallet.ui.main.history

import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.databinding.FragmentHistoryBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class HistoryFragment : BaseViewModelFragment(R.layout.fragment_history) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HistoryViewModel>
    override val viewModel: HistoryViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentHistoryBinding

    override fun initBinding() {
        binding = FragmentHistoryBinding.bind(requireView())
    }
}