package io.usoamic.wallet.custom.edit

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

open class SelectEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {
    private lateinit var selectDialog: AlertDialog

    protected var value: Any? = null
        set(value) {
            if (value != null) {
                field = value
                setText(getNameForValue(value))
            }
        }

    var items: List<CellData<*>> = listOf()
        set(value) {
            field = value
            initAlertDialog()
        }

    init {
        initListeners()
        initProperties()
    }

    private fun initListeners() {
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                showSelectDialog()
            }
        }
        setOnClickListener {
            showSelectDialog()
        }

        setOnKeyListener { _, _, _ ->
            true
        }
    }

    private fun initProperties() {
        inputType = android.text.InputType.TYPE_NULL
        setTextIsSelectable(false)
    }

    private fun initAlertDialog() {
        // Based on https://stackoverflow.com/questions/15762905/how-can-i-display-a-list-view-in-an-android-alert-dialog
        // setup the alert builder
        val builder = AlertDialog.Builder(context)

        // add a list
        val alertItems = items
            .map {
                getNameForValue(it.value)
            }
            .toTypedArray()

        builder.setItems(alertItems) { _, which ->
            value = items[which].value

        }

        selectDialog = builder.create()
    }

    private fun showSelectDialog() {
        // create and show the alert dialog
        selectDialog.show()
    }

    override fun onDetachedFromWindow() {
        if (::selectDialog.isInitialized) {
            selectDialog.dismiss()
        }
        super.onDetachedFromWindow()
    }

    private fun getNameForValue(value: Any): String {
        return items
            .find {
                it.value == value
            }?.name ?: throw IllegalStateException()
    }

    data class CellData<T : Any>(
        val name: String,
        val value: T
    )
}