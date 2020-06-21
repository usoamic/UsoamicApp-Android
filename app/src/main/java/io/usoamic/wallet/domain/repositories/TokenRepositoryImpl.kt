package io.usoamic.wallet.domain.repositories

import io.reactivex.Single
import io.usoamic.usoamickt.core.Usoamic
import io.usoamic.usoamickt.util.Coin
import io.usoamic.wallet.exceptions.ContractNullException
import io.usoamic.wallet.extensions.addDebugDelay
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val usoamic: Usoamic
) : TokenRepository {

    override val usoBalance: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamic.getUsoBalance().toCoin()
            }
                .addDebugDelay()
        }

    override val usoSupply: Single<BigDecimal>
        get() {
            return Single.fromCallable {
                usoamic.getSupply().toCoin()
            }
                .addDebugDelay()
        }

    private fun BigInteger?.toCoin(): BigDecimal {
        return this?.let {
            Coin.fromSat(it).toBigDecimal()
        } ?: throw ContractNullException("mapToCoin()")
    }
}