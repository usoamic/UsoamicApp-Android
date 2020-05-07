package io.usoamic.wallet.ui.single

import com.hadilq.liveevent.LiveEvent
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.SingleUseCase
import javax.inject.Inject

class SingleViewModel @Inject constructor(
    private val mUseCase: SingleUseCase
) : BaseViewModel() {
    val leLocked = LiveEvent<Unit>()

    fun checkThatNeedLock() {
        try {
            if (mUseCase.isNeedLocked()) {
                mUseCase.lockApp()
                leLocked.emit()
            }
        }
        catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}