package io.usoamic.app.ui.auth.unlock

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.usecases.UnlockUseCases
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.extensions.emit
import io.usoamic.app.ui.base.BaseViewModel
import io.usoamic.app.usecases.AppUseCases
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