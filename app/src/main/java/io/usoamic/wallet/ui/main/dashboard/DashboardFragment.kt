package io.usoamic.wallet.ui.main.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentDashboardBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseSrViewModelFragment
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardAdapter
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardItem
import javax.inject.Inject

class DashboardFragment : BaseSrViewModelFragment(R.layout.fragment_dashboard) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DashboardViewModel>
    override val viewModel: DashboardViewModel by viewModels { viewModelFactory }
    override val binding: FragmentDashboardBinding by viewBinding {
        FragmentDashboardBinding.bind(it.requireView())
    }
    override val srLayout: SwipeRefreshLayout
        get() = binding.srLayout

    private val dashboardAdapter = DashboardAdapter()

    override fun inject() {
        UsoamicWallet.component.dashboardSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldData, ::setData)
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
            pbContainer.progressBar.isVisible = isProgress
            srLayout.isInvisible = isProgress
    }

    private fun setData(list: List<DashboardItem>) {
        dashboardAdapter.addAll(list)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dashboardAdapter
        }
    }
}