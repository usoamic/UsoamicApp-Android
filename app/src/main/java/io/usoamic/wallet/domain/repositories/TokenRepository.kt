package io.usoamic.wallet.domain.repositories

import io.reactivex.Single
import io.usoamic.usoamickt.enumcls.TxSpeed
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.wallet.domain.models.history.TransactionItem
import java.math.BigDecimal
import java.math.BigInteger

interface TokenRepository {
    val usoBalance: Single<BigDecimal>
    val usoSupply: Single<BigDecimal>
    val numberOfUserTransactions: Single<BigInteger>
    fun getTransaction(
        txId: BigInteger
    ): Single<Transaction>

    fun getTransactionForAccount(
        txId: BigInteger
    ): Single<TransactionItem>

    fun transferUso(
        password: String,
        to: String,
        value: BigInteger,
        txSpeed: TxSpeed = TxSpeed.Auto
    ): Single<String>

    fun transferEth(
        password: String,
        to: String,
        value: BigInteger,
        txSpeed: TxSpeed = TxSpeed.Auto
    ): Single<String>
}