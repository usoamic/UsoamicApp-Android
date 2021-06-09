package io.usoamic.wallet.ui.auth.unlock

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.usecases.UnlockUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AppUseCases
import javax.inject.Inject

class UnlockViewModel @Inject constructor(
    private val mUseCases: UnlockUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.AUTH) {
    val leNext = LiveEvent<Unit>()

    fun onNextClick(password: String) {
        mUseCases.getAddress(password)
            .addSchedulers()
            .addProgress()
            .subscribe(::setData, ::throwError)
            .addToDisposable()
    }

    private fun setData(address: String) {
        mUseCases.saveData(address)
        leNext.emit()
    }
}