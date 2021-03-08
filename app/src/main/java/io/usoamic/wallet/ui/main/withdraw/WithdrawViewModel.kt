package io.usoamic.wallet.ui.main.withdraw

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.withdraw.WithdrawCoin
import io.usoamic.commons.crossplatform.usecases.WithdrawUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.ui.base.BaseViewModel
import javax.inject.Inject

class WithdrawViewModel @Inject constructor(
    private val mUseCases: WithdrawUseCases
) : BaseViewModel() {
    val leWithdraw = LiveEvent<String>()

    fun withdraw(
        coin: WithdrawCoin,
        password: String,
        to: String,
        value: String,
        gasPrice: String
    ) {
        mUseCases.withdraw(
            coin,
            password,
            to,
            value,
            gasPrice
        )
            .addSchedulers()
            .addProgress()
            .subscribe(::onWithdrawResult, ::throwError)
            .addToDisposable()
    }

    private fun onWithdrawResult(txHash: String) {
        leWithdraw.value = txHash
    }
}