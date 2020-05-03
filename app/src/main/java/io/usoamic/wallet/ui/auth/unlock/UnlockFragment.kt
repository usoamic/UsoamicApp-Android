package io.usoamic.wallet.ui.auth.unlock

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentUnlockBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class UnlockFragment : BaseViewModelFragment(R.layout.fragment_unlock) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<UnlockViewModel>
    override val viewModel: UnlockViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentUnlockBinding

    override fun inject() {
        UsoamicWallet.component.unlockSubcomponent.create().inject(this)
    }

    override fun initBinding() {
        binding = FragmentUnlockBinding.bind(requireView())
    }

    override fun showProgress(isProgress: Boolean) {
        binding.apply {
            pbContainer.progressBar.isVisible = isProgress
            clContainer.isInvisible = isProgress
        }
    }

    override fun initListeners() {
        super.initListeners()
        binding.apply {
            btnNext.setOnClickListener {
                viewModel.onNextClick()
            }

            btnForgot.setOnClickListener {
                viewModel.onForgotClick()
            }
        }
    }
}