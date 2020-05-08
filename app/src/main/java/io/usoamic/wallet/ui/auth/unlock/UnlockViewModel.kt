package io.usoamic.wallet.ui.auth.unlock

import com.hadilq.liveevent.LiveEvent
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.extensions.observeOnMain
import io.usoamic.wallet.extensions.subscribeOnIo
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.UnlockUseCase
import javax.inject.Inject

class UnlockViewModel @Inject constructor(
    private val mModel: UnlockUseCase
) : BaseViewModel() {
    val leNext = LiveEvent<Unit>()
    val leLogout = LiveEvent<Boolean>()

    fun onNextClick(password: String) {
        mModel.getAddress(password)
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe(::setData, ::throwError)
    }

    fun onLogoutClick() {
        mModel.removeAccount()
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe({
                onRemoveResult(true)
            }, {
                onRemoveResult(false)
            })
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