package io.usoamic.app.ui.auth.unlock

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentUnlockBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.observe
import io.usoamic.app.extensions.setVersion
import io.usoamic.app.ui.base.BaseViewModelFragment
import javax.inject.Inject
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import io.usoamic.app.extensions.value

class UnlockFragment : BaseViewModelFragment(R.layout.fragment_unlock) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<UnlockViewModel>
    override val viewModel: UnlockViewModel by viewModels { viewModelFactory }

    override val binding: FragmentUnlockBinding by viewBinding {
        FragmentUnlockBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicApp.component.unlockSubcomponent.create().inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.leNext) {
            goToWallet()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setVersion()
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        clContainer.isInvisible = isProgress
    }

    override fun initListeners() = with(binding) {
        super.initListeners()
        btnNext.setOnClickListener {
            viewModel.onNextClick(etPassword.value)
        }

        btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun goToWallet() {
        navigator.navigate(R.id.walletFragment)
    }
}