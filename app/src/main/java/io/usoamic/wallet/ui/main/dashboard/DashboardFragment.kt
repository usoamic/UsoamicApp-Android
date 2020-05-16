package io.usoamic.wallet.ui.main.dashboard

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
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
    override val binding: FragmentDashboardBinding by viewBinding {
        FragmentDashboardBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.dashboardSubcomponent.create().inject(this)
    }
}