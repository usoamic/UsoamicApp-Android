package io.usoamic.app.ui.main.history

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.commons.crossplatform.models.usecases.history.TransactionItem
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentHistoryBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.inflateLogout
import io.usoamic.app.extensions.observe
import io.usoamic.app.ui.base.BaseSrViewModelFragment
import io.usoamic.app.ui.main.history.adapter.HistoryAdapter
import javax.inject.Inject

class HistoryFragment : BaseSrViewModelFragment(R.layout.fragment_history) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HistoryViewModel>
    override val viewModel: HistoryViewModel by viewModels { viewModelFactory }
    override val srLayout: SwipeRefreshLayout
        get() = binding.srLayout

    private val historyAdapter = HistoryAdapter()

    override val binding: FragmentHistoryBinding by viewBinding {
        FragmentHistoryBinding.bind(it.requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar, false)
        setHasOptionsMenu(true)

        initRecyclerView()
    }

    override fun inject() {
        UsoamicApp.component.historySubcomponent.create().inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldData, ::setData)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflateLogout(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setData(list: List<TransactionItem>) {
        binding.viewNoData.isVisible = list.isEmpty()
        historyAdapter.addAll(list)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        srLayout.isInvisible = isProgress
    }
}