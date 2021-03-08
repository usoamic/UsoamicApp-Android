package io.usoamic.wallet.ui.base

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import io.usoamic.commons.crossplatform.models.base.ErrorArguments
import io.usoamic.wallet.R
import io.usoamic.wallet.extensions.showErrorDialogWithMessage


abstract class BaseFragment(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    protected lateinit var errorDialog: AlertDialog
    protected abstract val binding: ViewBinding

    protected val navigator: NavController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutRes, container, false)
    }

    protected open fun inject() = Unit
    protected open fun initListeners() = Unit
    protected open fun showProgress(isProgress: Boolean) = Unit

    protected fun setSupportActionBar(toolbar: Toolbar, withBackButton: Boolean = true) {
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(toolbar)
            if (withBackButton) {
                supportActionBar?.apply {
                    setDisplayHomeAsUpEnabled(true)
                    setDisplayShowHomeEnabled(true)
                }
            }
        }
        toolbar.setNavigationOnClickListener {
            onBackButtonPressed()
        }
    }

    protected open fun onBackButtonPressed() {
        (requireActivity() as? AppCompatActivity)?.onBackPressed()
    }

    protected fun requireSupportActionBar() =
        (requireActivity() as? AppCompatActivity)?.supportActionBar!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    protected open fun showErrorDialog(error: String) = showErrorDialog(error, false)

    protected open fun showErrorDialog(error: String, isFinish: Boolean) {
        errorDialog = showErrorDialogWithMessage(
            error,
            if (isFinish) {
                DialogInterface.OnClickListener { _, _ ->
                    onBackButtonPressed()
                }
            } else {
                null
            }
        )
    }

    protected open fun showErrorDialog(error: ErrorArguments) {
        showErrorDialog(
            error.message ?: getString(R.string.unknown_error),
            (error is ErrorArguments.Fatal)
        )
    }

    protected fun hideKeyboard() {
        //Source: https://stackoverflow.com/questions/1109022/close-hide-android-soft-keyboard
        activity?.let {
            val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().windowToken, 0)
        }

    }

    override fun onDestroyView() {
        if (::errorDialog.isInitialized) {
            errorDialog.dismiss()
        }
        super.onDestroyView()
    }
}