package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import java.math.BigDecimal

interface TokenRepository {
    fun getSupply(): Single<BigDecimal>
}