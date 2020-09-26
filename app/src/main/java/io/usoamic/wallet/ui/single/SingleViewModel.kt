package io.usoamic.wallet.ui.single

import com.hadilq.liveevent.LiveEvent
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.SingleUseCases
import javax.inject.Inject

class SingleViewModel @Inject constructor(
    private val mUseCases: SingleUseCases
) : BaseViewModel() {
    val leLocked = LiveEvent<Unit>()

    fun checkThatNeedLock() {
        mUseCases.hasAccount()
            .addSchedulers()
            .subscribe(::tryLockApp, ::throwError)
            .addToDisposable()
    }

    private fun tryLockApp(hasAccount: Boolean) {
        if (hasAccount && mUseCases.isNeedLocked()) {
            mUseCases.lockApp()
            leLocked.emit()
        }
    }
}