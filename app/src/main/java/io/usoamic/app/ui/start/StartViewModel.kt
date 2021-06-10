package io.usoamic.app.ui.start

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.app.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.StartUseCases
import io.usoamic.app.extensions.addSchedulers

import io.usoamic.app.ui.base.BaseViewModel
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val mStartUseCases: StartUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.AUTH) {
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