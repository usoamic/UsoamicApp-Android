package io.usoamic.wallet.ui.start

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.extensions.addSchedulers

import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.StartUseCases
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val mStartUseCases: StartUseCases
) : BaseViewModel() {
    val ldHasAccount = MutableLiveData<Boolean>()

    init {
        checkAccount()
    }

    private fun checkAccount() {
        mStartUseCases.hasAccount()
            .addSchedulers()
            .addProgress()
            .subscribe({
                ldHasAccount.value = it
            }, ::throwErrorAndFinish)
            .addToDisposable()
    }
}