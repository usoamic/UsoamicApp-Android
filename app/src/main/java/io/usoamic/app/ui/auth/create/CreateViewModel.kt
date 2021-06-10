package io.usoamic.app.ui.auth.create

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.ethereum.AccountCredentialsModel
import io.usoamic.app.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.CreateAccountUseCases
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.ui.base.BaseViewModel
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