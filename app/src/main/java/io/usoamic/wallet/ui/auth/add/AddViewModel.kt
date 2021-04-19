package io.usoamic.wallet.ui.auth.add

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.base.ScreenTag
import io.usoamic.commons.crossplatform.usecases.AddAccountUseCases
import io.usoamic.wallet.usecases.AppUseCases
import io.usoamic.wallet.extensions.addSchedulers

import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.ui.base.BaseViewModel
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val mModel: AddAccountUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.AUTH) {
    val leAccountAdd = LiveEvent<Unit>()

    fun onAddClick(privateKey: String, password: String, confirmPassword: String) {
        mModel.addAccount(privateKey, password, confirmPassword)
            .addSchedulers()
            .addProgress()
            .subscribe({
                leAccountAdd.emit()
            }, ::throwError)
            .addToDisposable()
    }
}