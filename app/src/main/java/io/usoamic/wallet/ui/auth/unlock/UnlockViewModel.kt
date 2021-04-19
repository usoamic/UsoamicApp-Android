package io.usoamic.wallet.ui.auth.unlock

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.base.ScreenTag
import io.usoamic.commons.crossplatform.usecases.UnlockUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AppUseCases
import javax.inject.Inject

class UnlockViewModel @Inject constructor(
    private val mModel: UnlockUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.AUTH) {
    val leNext = LiveEvent<Unit>()
    val leLogout = LiveEvent<Boolean>()

    fun onNextClick(password: String) {
        mModel.getAddress(password)
            .addSchedulers()
            .addProgress()
            .subscribe(::setData, ::throwError)
            .addToDisposable()
    }

    fun onLogoutClick() {
        mModel.clearDb()
            .onErrorComplete()
            .andThen(mModel.removeAccount())
            .addSchedulers()
            .addProgress()
            .subscribe({
                onRemoveResult(true)
            }, {
                onRemoveResult(false)
            })
            .addToDisposable()
    }

    private fun onRemoveResult(isRemoved: Boolean) {
        mModel.removePreferences()
        leLogout.emit(isRemoved)
    }

    private fun setData(address: String) {
        mModel.saveData(address)
        leNext.emit()
    }
}