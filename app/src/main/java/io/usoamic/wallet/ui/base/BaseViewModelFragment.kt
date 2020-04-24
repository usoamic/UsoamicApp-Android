package io.usoamic.wallet.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import io.usoamic.wallet.extensions.observe

abstract class BaseViewModelFragment(
    @LayoutRes private val layoutRes: Int
) : BaseFragment(layoutRes) {
    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    protected open fun initObservers() {
        observe(viewModel.ldThrowable, ::showErrorDialog)
        observe(viewModel.ldError, ::showErrorDialog)
        observe(viewModel.ldProgress, ::showProgress)
    }
}