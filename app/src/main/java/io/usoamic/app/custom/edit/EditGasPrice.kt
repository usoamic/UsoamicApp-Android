package io.usoamic.app.custom.edit

import android.content.Context
import android.util.AttributeSet
import io.usoamic.usoamickt.enumcls.TxSpeed
import io.usoamic.app.R
import io.usoamic.app.extensions.getString

class EditGasPrice @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SelectEditText(context, attrs, defStyleAttr) {
    init {
        initSelectEditText()
        initContent()
    }

    private fun initContent() {
        value = TxSpeed.Auto
        hint = getString(R.string.gas_price)
    }

    private fun initSelectEditText() {
        items = listOf(
            CellData(
                name = getString(R.string.gp_auto),
                value = TxSpeed.Auto
            ),
            CellData(
                name = getString(R.string.gp_20),
                value = TxSpeed.GP20
            ),
            CellData(
                name = getString(R.string.gp_40),
                value = TxSpeed.GP40
            ),
            CellData(
                name = getString(R.string.gp_60),
                value = TxSpeed.GP60
            ),
            CellData(
                name = getString(R.string.gp_80),
                value = TxSpeed.GP80
            ),
            CellData(
                name = getString(R.string.gp_100),
                value = TxSpeed.GP100
            ),
            CellData(
                name = getString(R.string.gp_120),
                value = TxSpeed.GP120
            )
        )
    }
}