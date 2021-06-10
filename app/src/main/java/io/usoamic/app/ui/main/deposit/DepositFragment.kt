package io.usoamic.app.ui.main.deposit

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import io.usoamic.app.R
import io.usoamic.app.UsoamicApp
import io.usoamic.app.databinding.FragmentDepositBinding
import io.usoamic.app.di.other.ViewModelFactory
import io.usoamic.app.extensions.copyToClipboard
import io.usoamic.app.extensions.inflateLogout
import io.usoamic.app.extensions.observe
import io.usoamic.app.models.DepositInfo
import io.usoamic.app.ui.base.BaseViewModelFragment
import javax.inject.Inject
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

class DepositFragment : BaseViewModelFragment(R.layout.fragment_deposit) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DepositViewModel>
    override val viewModel: DepositViewModel by viewModels { viewModelFactory }
    override val binding: FragmentDepositBinding by viewBinding {
        FragmentDepositBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicApp.component.depositSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar, false)
        setHasOptionsMenu(true)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.ldData, ::setData)
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        llContainer.isInvisible = isProgress
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflateLogout(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setData(info: DepositInfo) = with(binding) {
        val address = info.address
        tvAddress.text = address
        llContainer.setOnClickListener {
            copyToClipboard(address)
        }
        ivQrCode.setImageBitmap(info.qrCode)
    }
}