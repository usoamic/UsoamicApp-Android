package io.usoamic.wallet.domain.repositories

import io.reactivex.Single
import java.math.BigDecimal

interface TokenRepository {
    val usoBalance: Single<BigDecimal>
    val usoSupply: Single<BigDecimal>
}