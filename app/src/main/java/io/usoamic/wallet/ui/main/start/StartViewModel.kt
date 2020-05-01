package io.usoamic.wallet.ui.main.start

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.extensions.observeOnMain
import io.usoamic.wallet.extensions.subscribeOnIo
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
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe({
                ldHasAccount.value = it
            }, ::throwErrorAndFinish)
    }
}