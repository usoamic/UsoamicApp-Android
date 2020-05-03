package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.usoamic.usoamickt.core.Usoamic
import io.usoamic.usoamickt.util.Coin
import io.usoamic.wallet.exceptions.ContractNullException
import io.usoamic.wallet.extensions.addDebugDelay
import java.math.BigDecimal
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val usoamic: Usoamic
) : TokenRepository {
    override fun getSupply(): Single<BigDecimal> {
        return Single.fromCallable {
            usoamic.getSupply()
        }.map { bi ->
            bi?.let {
                Coin.fromSat(it).toBigDecimal()
            } ?: throw ContractNullException(this::getSupply.name)
        }.addDebugDelay()
    }

}