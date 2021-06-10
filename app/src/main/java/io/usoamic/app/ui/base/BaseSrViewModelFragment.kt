package io.usoamic.app.ui.base

import androidx.annotation.LayoutRes
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.usoamic.app.extensions.observe

abstract class BaseSrViewModelFragment(
    @LayoutRes private val layoutRes: Int
) : BaseViewModelFragment(layoutRes) {
    abstract override val viewModel: BaseSrViewModel
    abstract val srLayout: SwipeRefreshLayout

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldShowSwipeRefresh, ::showSwipeRefresh)
    }

    override fun initListeners() {
        srLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun showSwipeRefresh(isProgress: Boolean) {
        srLayout.isRefreshing = isProgress
    }
}