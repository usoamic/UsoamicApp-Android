package io.usoamic.app.ui.single

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.exceptions.PreferenceKeyNotFoundThrowable
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.extensions.emit
import io.usoamic.app.ui.base.BaseViewModel
import io.usoamic.app.usecases.AppUseCases
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