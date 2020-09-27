package io.usoamic.wallet.ui.main.deposit

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentAddBinding
import io.usoamic.wallet.databinding.FragmentDepositBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.domain.models.deposit.DepositInfo
import io.usoamic.wallet.extensions.copyToClipboard
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject

class DepositFragment : BaseViewModelFragment(R.layout.fragment_deposit) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DepositViewModel>
    override val viewModel: DepositViewModel by viewModels { viewModelFactory }
    override val binding: FragmentDepositBinding by viewBinding {
        FragmentDepositBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.depositSubcomponent.create().inject(this)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldData, ::setData)
    }

    override fun showProgress(isProgress: Boolean) {
        binding.apply {
            pbContainer.progressBar.isVisible = isProgress
            llContainer.isInvisible = isProgress
        }
    }

    private fun setData(info: DepositInfo) {
        val address = info.address
        binding.apply {
            tvAddress.text = address
            llContainer.setOnClickListener {
                copyToClipboard(address)
            }
            ivQrCode.setImageBitmap(info.qrCode)
        }
    }
}