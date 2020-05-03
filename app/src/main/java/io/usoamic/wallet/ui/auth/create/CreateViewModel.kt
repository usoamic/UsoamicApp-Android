package io.usoamic.wallet.ui.auth.create

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.domain.models.ethereum.AccountCredentials
import io.usoamic.wallet.extensions.observeOnMain
import io.usoamic.wallet.extensions.subscribeOnIo
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.CreateAccountUseCase
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    mModel: CreateAccountUseCase
) : BaseViewModel() {
    val ldContent = MutableLiveData<AccountCredentials>()

    init {
        mModel.createCredentials()
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe(::setContent, ::throwErrorAndFinish)
            .addToDisposable()
    }

    private fun setContent(credentials: AccountCredentials) {
        ldContent.value = credentials
    }
}