package io.usoamic.wallet.ui.main.deposit

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.domain.models.deposit.DepositInfo
import io.usoamic.wallet.extensions.observeOnMain
import io.usoamic.wallet.extensions.subscribeOnIo
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.DepositUseCase
import javax.inject.Inject

class DepositViewModel @Inject constructor(
    private val mDepositUseCase: DepositUseCase
) : BaseViewModel() {
    val ldData = MutableLiveData<DepositInfo>()

    init {
        getAddress()
    }

    private fun getAddress() {
        val address = mDepositUseCase.getAddress()
        mDepositUseCase.generateQrCode(address)
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe({
                ldData.value = DepositInfo(address, it)
            }, ::throwError)
    }
}