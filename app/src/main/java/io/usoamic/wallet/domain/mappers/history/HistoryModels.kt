package io.usoamic.wallet.domain.mappers.history

import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionTypeEntity
import io.usoamic.usoamickt.util.Coin
import io.usoamic.wallet.domain.models.realm.TransactionItemRealm

fun TransactionItemRealm.toEntity(): TransactionEntity = TransactionEntity(
    type = TransactionTypeEntity.valueOf(type!!),
    txId = txId.toBigInteger(),
    from = from!!,
    to = to!!,
    value = Coin.fromCoin(value!!),
    timestamp = timestamp!!.toBigInteger()
)

fun TransactionEntity.toRealm(): TransactionItemRealm = TransactionItemRealm(
    type = type.toString(),
    txId = txId.toLong(),
    from = from,
    to = to,
    value = value.toBigDecimal().toPlainString(),
    timestamp = timestamp.toLong()
)
/*
fun Transaction.toEntity(owner: String): TransactionEntity = TransactionEntity(
    type = when (owner) {
        from -> {
            TransactionTypeEntity.WITHDRAW
        }
        to -> {
            TransactionTypeEntity.DEPOSIT
        }
        else -> throw IllegalArgumentException()
    },
    txId = txId,
    from = from,
    to = to,
    value = Coin.fromCoin(value),
    timestamp = timestamp
)

 */