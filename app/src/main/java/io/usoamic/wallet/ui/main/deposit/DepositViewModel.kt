package io.usoamic.wallet.ui.main.deposit

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.domain.models.deposit.DepositInfo
import io.usoamic.wallet.extensions.addSchedulers

import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.DepositUseCases
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
                ldData.value = DepositInfo(address, it)
            }, ::throwError)
            .addToDisposable()
    }
}