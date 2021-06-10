package io.usoamic.app.ui.auth.add

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.usecases.AddAccountUseCases
import io.usoamic.app.usecases.AppUseCases
import io.usoamic.app.extensions.addSchedulers

import io.usoamic.app.extensions.emit
import io.usoamic.app.ui.base.BaseViewModel
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