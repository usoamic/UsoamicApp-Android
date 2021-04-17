package io.usoamic.wallet.ui.single

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.exceptions.PreferenceKeyNotFoundThrowable
import io.usoamic.commons.crossplatform.models.base.ScreenTag
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AppUseCases
import javax.inject.Inject

class SingleViewModel @Inject constructor(
        private val mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.AUTH) {
    val leLocked = LiveEvent<Unit>()

    fun checkThatNeedLock() {
        mAppUseCases.hasAccount()
            .addSchedulers()
            .subscribe(::tryLockApp, ::throwError)
            .addToDisposable()
    }

    private fun tryLockApp(hasAccount: Boolean) {
        try {
            if (hasAccount && mAppUseCases.isNeedLocked()) {
                mAppUseCases.lockApp()
                leLocked.emit()
            }
        }
        catch (e: PreferenceKeyNotFoundThrowable) {

        }
    }
}