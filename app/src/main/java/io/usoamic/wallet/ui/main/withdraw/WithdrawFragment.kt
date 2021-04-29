package io.usoamic.wallet.ui.main.withdraw

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.*
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.zxing.integration.android.IntentIntegrator
import io.usoamic.commons.crossplatform.models.usecases.withdraw.WithdrawCoinTicker
import io.usoamic.usoamickt.enumcls.TxSpeed
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

    private lateinit var txSpeedDialog: AlertDialog

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
        initTxSpeed()
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
        return false
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

    private fun initTxSpeed() = with(binding.etTxSpeed) {
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showSelectGasPriceDialog()
            }
        }
        setOnClickListener {
            showSelectGasPriceDialog()
        }
        inputType = InputType.TYPE_NULL
        setTextIsSelectable(false)
        setOnKeyListener { _: View, _: Int, _: KeyEvent ->
            true
        }
    }

    private fun showSelectGasPriceDialog() {
        // Based on https://stackoverflow.com/questions/15762905/how-can-i-display-a-list-view-in-an-android-alert-dialog
        // setup the alert builder
        val builder = AlertDialog.Builder(context)

        // add a list
        val gasPrices = arrayOf(
            getString(R.string.gp_auto),
            getString(R.string.gp_20),
            getString(R.string.gp_40),
            getString(R.string.gp_60),
            getString(R.string.gp_80),
            getString(R.string.gp_100),
            getString(R.string.gp_120)
        )

        builder.setItems(gasPrices) { _, which ->
            val txSpeed = when (which) {
                1 -> TxSpeed.GP20
                2 -> TxSpeed.GP40
                3 -> TxSpeed.GP60
                4 -> TxSpeed.GP80
                5 -> TxSpeed.GP100
                6 -> TxSpeed.GP120
                else -> TxSpeed.Auto
            }
            setGasPrice(txSpeed)
        }

        // create and show the alert dialog
        txSpeedDialog = builder.create()
        txSpeedDialog.show()
    }

    private fun setGasPrice(txSpeed: TxSpeed) {
        val gasPriceResId = when (txSpeed) {
            TxSpeed.Auto -> R.string.gp_auto
            TxSpeed.GP20 -> R.string.gp_20
            TxSpeed.GP40 -> R.string.gp_40
            TxSpeed.GP60 -> R.string.gp_60
            TxSpeed.GP80 -> R.string.gp_80
            TxSpeed.GP100 -> R.string.gp_100
            TxSpeed.GP120 -> R.string.gp_120
        }

        binding.etTxSpeed.setText(getString(gasPriceResId))
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

    override fun onDestroyView() {
        if (::txSpeedDialog.isInitialized) {
            txSpeedDialog.dismiss()
        }
        super.onDestroyView()
    }
}