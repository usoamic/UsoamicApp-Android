package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.reactivex.functions.Function3
import io.usoamic.usoamickt.enumcls.TxSpeed
import io.usoamic.usoamickt.util.Coin
import io.usoamic.wallet.domain.models.withdraw.WithdrawCoin
import io.usoamic.wallet.domain.models.withdraw.WithdrawData
import io.usoamic.wallet.domain.repositories.TokenRepository
import io.usoamic.wallet.domain.repositories.ValidateRepository
import javax.inject.Inject

class WithdrawUseCases @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mTokenRepository: TokenRepository
) {
    fun withdraw(
        coin: WithdrawCoin,
        password: String,
        to: String,
        value: String,
        txSpeed: String
    ): Single<String> {
        return Single.zip(
            mValidateRepository.validatePassword(password),
            mValidateRepository.validateAddress(to),
            mValidateRepository.validateTransferValue(value),
            Function3 { p: Boolean, a: Boolean, tv: Boolean ->
                p && a && tv
            }
        )
            .flatMap {
                mTokenRepository.withdraw(
                    WithdrawData(
                        coin = coin,
                        password = password,
                        to = to,
                        value = Coin.fromCoin(value).toSat(),
                        txSpeed = TxSpeed.parseString(txSpeed)
                    )
                )
            }
    }
}