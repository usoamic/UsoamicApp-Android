package io.usoamic.wallet.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.usoamic.wallet.R
import io.usoamic.wallet.extensions.observe
import io.usoamic.wallet.extensions.string


abstract class BaseFragment(
    @LayoutRes val layoutRes: Int
) : Fragment() {
    protected abstract val viewModel: BaseViewModel
    protected lateinit var errorDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    protected open fun setObservers() {
        observe(viewModel.ldThrowable, ::showErrorDialog)
        observe(viewModel.ldError, ::showErrorDialog)
        observe(viewModel.ldProgress, ::showProgress)
    }

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

    override fun onDestroyView() {
        if(::errorDialog.isInitialized) {
            errorDialog.dismiss()
        }
        super.onDestroyView()
    }
}