package io.usoamic.wallet.ui.main.history

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.commons.crossplatform.models.history.TransactionItem
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentHistoryBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseSrViewModelFragment
import io.usoamic.wallet.ui.main.history.adapter.HistoryAdapter
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
        initRecyclerView()
    }

    override fun inject() {
        UsoamicWallet.component.historySubcomponent.create().inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldData, ::setData)
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

    override fun showProgress(isProgress: Boolean) {
        binding.apply {
            pbContainer.progressBar.isVisible = isProgress
            srLayout.isInvisible = isProgress
        }
    }
}