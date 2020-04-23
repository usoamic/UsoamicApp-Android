package io.usoamic.wallet.ui.base

import android.app.Activity
import android.app.AlertDialog
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
import io.usoamic.wallet.R
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.string


abstract class BaseFragment(
    @LayoutRes val layoutRes: Int
) : Fragment() {
    protected abstract val viewModel: BaseViewModel

    protected lateinit var errorDialog: AlertDialog

    protected val navigator: NavController by lazy {
        NavHostFragment.findNavController(this)
    }

    protected fun setSupportActionBar(toolbar: Toolbar, withBackButton: Boolean = true) {
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(toolbar)
            if(withBackButton) {
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

    protected fun requireSupportActionBar() = (requireActivity() as? AppCompatActivity)?.supportActionBar!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initObservers()
        initListeners()
    }

    protected open fun initObservers() {
        observe(viewModel.ldThrowable, ::showErrorDialog)
        observe(viewModel.ldError, ::showErrorDialog)
        observe(viewModel.ldProgress, ::showProgress)
    }

    protected open fun initBinding() = Unit

    protected open fun initListeners() = Unit

    protected open fun showProgress(isProgress: Boolean) = Unit

    protected open fun showErrorDialog(error: String) {
        errorDialog = AlertDialog.Builder(requireContext())
            .setTitle(string(R.string.error))
            .setMessage(error)
            .setPositiveButton(android.R.string.ok, null)
            .setIcon(R.drawable.ic_launcher_foreground)
            .show()
    }

    protected open fun showErrorDialog(error: Throwable) = showErrorDialog(error.message ?: string(R.string.unknown_error))

    protected fun hideKeyboard() {
        //Source: https://stackoverflow.com/questions/1109022/close-hide-android-soft-keyboard
        activity?.let {
            val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().windowToken, 0)
        }

    }

    override fun onDestroyView() {
        if(::errorDialog.isInitialized) {
            errorDialog.dismiss()
        }
        super.onDestroyView()
    }
}