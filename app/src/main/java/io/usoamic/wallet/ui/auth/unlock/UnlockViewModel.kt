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

    fun onNextClick(password: String) {
        mModel.saveAddress(password)
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe({
                leNext.emit()
            }, ::throwError)
    }

    fun onForgotClick() {

    }
}