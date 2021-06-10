package io.usoamic.app.ui.main.withdraw

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.withdraw.WithdrawCoinTicker
import io.usoamic.app.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.WithdrawUseCases
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.ui.base.BaseViewModel
import javax.inject.Inject

class WithdrawViewModel @Inject constructor(
    private val mUseCases: WithdrawUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.WALLET) {
    val leWithdraw = LiveEvent<String>()

    fun withdraw(
        coin: WithdrawCoinTicker,
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