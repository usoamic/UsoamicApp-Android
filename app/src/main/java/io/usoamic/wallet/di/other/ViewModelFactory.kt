package io.usoamic.wallet.di.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/*
 * https://github.com/google/dagger/issues/1273
 * https://stackoverflow.com/questions/59381817/injecting-viewmodel-into-the-fragment-with-dagger-2-with-parameters
 */
class ViewModelFactory<VM> @Inject constructor(private val viewModel: Lazy<VM>)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return viewModel.get() as T
    }
}
