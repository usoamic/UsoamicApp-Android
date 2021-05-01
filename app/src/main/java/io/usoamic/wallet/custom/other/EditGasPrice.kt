package io.usoamic.wallet.custom.other

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import io.usoamic.usoamickt.enumcls.TxSpeed
import io.usoamic.wallet.R
import io.usoamic.wallet.extensions.getString

class EditGasPrice @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {
    private lateinit var txSpeedDialog: AlertDialog

    init {
        initContent()
        initTxSpeed()
    }

    private fun initContent() {
        setGasPrice(TxSpeed.Auto)
        hint = getString(R.string.gas_price)
    }

    private fun initTxSpeed() {
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showSelectGasPriceDialog()
            }
        }
        setOnClickListener {
            showSelectGasPriceDialog()
        }

        inputType = android.text.InputType.TYPE_NULL

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
            TxSpeed.Auto,
            TxSpeed.GP20,
            TxSpeed.GP40,
            TxSpeed.GP60,
            TxSpeed.GP80,
            TxSpeed.GP100,
            TxSpeed.GP120
        )
            .map(::getStringForGasPrice)
            .toTypedArray()

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
        setText(
            getStringForGasPrice(txSpeed)
        )
    }

    override fun onDetachedFromWindow() {
        if (::txSpeedDialog.isInitialized) {
            txSpeedDialog.dismiss()
        }
        super.onDetachedFromWindow()
    }

    private fun getStringForGasPrice(txSpeed: TxSpeed): String {
        val resId = when (txSpeed) {
            TxSpeed.Auto -> R.string.gp_auto
            TxSpeed.GP20 -> R.string.gp_20
            TxSpeed.GP40 -> R.string.gp_40
            TxSpeed.GP60 -> R.string.gp_60
            TxSpeed.GP80 -> R.string.gp_80
            TxSpeed.GP100 -> R.string.gp_100
            TxSpeed.GP120 -> R.string.gp_120
        }
        return getString(resId)
    }
}