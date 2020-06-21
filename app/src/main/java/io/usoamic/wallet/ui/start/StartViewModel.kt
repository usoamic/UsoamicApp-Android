package io.usoamic.wallet.ui.start

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.extensions.addSchedulers

import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.StartUseCase
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val mStartUseCase: StartUseCase
) : BaseViewModel() {
    val ldHasAccount = MutableLiveData<Boolean>()

    init {
        checkAccount()
    }

    private fun checkAccount() {
        mStartUseCase.hasAccount()
            .addSchedulers()
            .addProgress()
            .subscribe({
                ldHasAccount.value = it
            }, ::throwErrorAndFinish)
            .addToDisposable()
    }
}