package io.usoamic.app.ui.main.dashboard

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentDashboardBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.inflateLogout
import io.usoamic.app.extensions.observe
import io.usoamic.app.ui.base.BaseSrViewModelFragment
import io.usoamic.app.ui.main.dashboard.adapter.DashboardAdapter
import io.usoamic.app.ui.main.dashboard.adapter.DashboardItem
import javax.inject.Inject
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager

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
        UsoamicApp.component.dashboardSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar, false)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflateLogout(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setData(list: List<DashboardItem>) {
        dashboardAdapter.addAll(list)
    }

    private fun initRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = dashboardAdapter
    }
}
