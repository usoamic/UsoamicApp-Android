package io.usoamic.wallet.ui.main.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentDashboardBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardAdapter
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardItem
import javax.inject.Inject

class DashboardFragment : BaseViewModelFragment(R.layout.fragment_dashboard) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DashboardViewModel>
    override val viewModel: DashboardViewModel by viewModels { viewModelFactory }
    override val binding: FragmentDashboardBinding by viewBinding {
        FragmentDashboardBinding.bind(it.requireView())
    }

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
        observe(viewModel.ldShowSwipeRefresh, ::showSwipeRefresh)
    }

    override fun initListeners() {
        binding.srLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun setData(list: List<DashboardItem>) {
        dashboardAdapter.addAll(list)
    }

    private fun showSwipeRefresh(isProgress: Boolean) {
        binding.srLayout.isRefreshing = isProgress
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dashboardAdapter
        }
    }
}