package io.usoamic.wallet.ui.main.deposit

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.usecases.DepositUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.toBitmap
import io.usoamic.wallet.models.DepositInfo
import io.usoamic.wallet.ui.base.BaseViewModel
import javax.inject.Inject

class DepositViewModel @Inject constructor(
    private val mDepositUseCases: DepositUseCases
) : BaseViewModel() {
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