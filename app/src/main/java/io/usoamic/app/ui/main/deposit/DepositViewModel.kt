package io.usoamic.app.ui.main.deposit

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.app.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.DepositUseCases
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.extensions.toBitmap
import io.usoamic.app.models.DepositInfo
import io.usoamic.app.ui.base.BaseViewModel
import javax.inject.Inject

class DepositViewModel @Inject constructor(
    private val mDepositUseCases: DepositUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.WALLET) {
    val ldData = MutableLiveData<DepositInfo>()

    init {
        getAddress()
    }

    private fun getAddress() {
        val address = mDepositUseCases.getAddress()
        mDepositUseCases.generateQrCode(address)
            .addSchedulers()
            .addProgress()
            .subscribe({
                ldData.value = DepositInfo(address, it.toBitmap())
            }, ::throwError)
            .addToDisposable()
    }
}