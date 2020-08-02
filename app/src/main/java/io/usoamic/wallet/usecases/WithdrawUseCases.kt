package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.usoamickt.enumcls.TxSpeed
import io.usoamic.wallet.domain.models.withdraw.WithdrawCoin
import io.usoamic.wallet.domain.models.withdraw.WithdrawData
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigInteger
import javax.inject.Inject

class WithdrawUseCases @Inject constructor(
    private val mTokenRepository: TokenRepository
) {
    fun withdraw(
        coin: WithdrawCoin,
        password: String,
        to: String,
        value: BigInteger,
        txSpeed: TxSpeed
    ): Single<String> {
        return mTokenRepository.withdraw(
            WithdrawData(
                coin = coin,
                password = password,
                to = to,
                value = value,
                txSpeed = txSpeed
            )
        )
    }
}