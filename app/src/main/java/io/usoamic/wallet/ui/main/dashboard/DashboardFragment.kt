package io.usoamic.wallet.ui.main.dashboard

import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentDashboardBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class DashboardFragment : BaseViewModelFragment(R.layout.fragment_dashboard) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DashboardViewModel>
    override val viewModel: DashboardViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentDashboardBinding

    override fun inject() {
        UsoamicWallet.component.dashboardSubcomponent.create().inject(this)
    }

    override fun initBinding() {
        binding = FragmentDashboardBinding.bind(requireView())
    }
}