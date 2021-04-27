package io.usoamic.wallet.ui.auth.create

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.ethereum.AccountCredentialsModel
import io.usoamic.wallet.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.CreateAccountUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.ui.base.BaseViewModel
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    mModel: CreateAccountUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.AUTH) {
    val ldContent = MutableLiveData<AccountCredentialsModel>()

    init {
        mModel.createCredentials()
            .addSchedulers()
            .addProgress()
            .subscribe(::setContent, ::throwErrorAndFinish)
            .addToDisposable()
    }

    private fun setContent(credentials: AccountCredentialsModel) {
        ldContent.value = credentials
    }
}