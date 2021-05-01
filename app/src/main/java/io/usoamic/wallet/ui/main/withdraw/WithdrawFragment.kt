package io.usoamic.wallet.ui.main.withdraw

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.zxing.integration.android.IntentIntegrator
import io.usoamic.commons.crossplatform.models.usecases.withdraw.WithdrawCoinTicker
import io.usoamic.wallet.R
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.databinding.FragmentWithdrawBinding
import io.usoamic.wallet.di.other.ViewModelFactory
import io.usoamic.wallet.extensions.clear
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.showMessage
import io.usoamic.wallet.extensions.value
import io.usoamic.wallet.ui.base.BaseViewModelFragment
import javax.inject.Inject


class WithdrawFragment : BaseViewModelFragment(R.layout.fragment_withdraw) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<WithdrawViewModel>
    override val viewModel: WithdrawViewModel by viewModels { viewModelFactory }



    override val binding: FragmentWithdrawBinding by viewBinding {
        FragmentWithdrawBinding.bind(it.requireView())
    }

    override fun inject() {
        UsoamicWallet.component.withdrawSubcomponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(binding.toolbar, false)
        setHasOptionsMenu(true)
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.leWithdraw, ::setAsWithdrawn)
    }

    private fun setAsWithdrawn(message: String) {
        binding.etPassword.clear()
        showMessage(message)
    }

    override fun showProgress(isProgress: Boolean) = with(binding) {
        pbContainer.progressBar.isVisible = isProgress
        clContainer.isInvisible = isProgress
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            inflater.inflate(R.menu.withdraw_menu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.scanQrCode -> scanQrCode()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun scanQrCode() {
        IntentIntegrator.forSupportFragment(this).initiateScan()
    }

    override fun initListeners() = with(binding) {
        super.initListeners()
        btnWithdrawEth.setOnClickListener {
            withdraw(WithdrawCoinTicker.ETH)
        }

        btnWithdrawUso.setOnClickListener {
            withdraw(WithdrawCoinTicker.USO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
               binding.etAddress.setText(result.contents)
            }
        }
    }

    private fun withdraw(
        coin: WithdrawCoinTicker
    ) = with(binding) {
        viewModel.withdraw(
            coin = coin,
            password = etPassword.value,
            to = etAddress.value,
            value = etValue.value,
            gasPrice = etTxSpeed.value
        )
    }
}