package io.usoamic.wallet.domain.repositories

import io.reactivex.Single
import io.usoamic.usoamickt.model.Transaction
import java.math.BigDecimal
import java.math.BigInteger

interface TokenRepository {
    val usoBalance: Single<BigDecimal>
    val usoSupply: Single<BigDecimal>
    val numberOfUserTransactions: Single<BigInteger>
    fun getTransaction(txId: BigInteger): Single<Transaction>
}