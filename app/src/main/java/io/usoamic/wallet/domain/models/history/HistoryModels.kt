package io.usoamic.wallet.domain.models.history

import io.usoamic.commons.crossplatform.models.history.TransactionItem
import io.usoamic.commons.crossplatform.models.history.TransactionType
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.usoamickt.util.Coin
import io.usoamic.wallet.domain.models.realm.TransactionItemRealm

fun TransactionItemRealm.toDomain(): TransactionItem = TransactionItem(
    type = TransactionType.valueOf(type!!),
    txId = txId,
    from = from!!,
    to = to!!,
    value = Coin.fromCoin(value!!),
    timestamp = timestamp!!.toLong()
)

fun TransactionItem.toRealm(): TransactionItemRealm = TransactionItemRealm(
    type = type.toString(),
    txId = txId,
    from = from,
    to = to,
    value = value.toBigDecimal().toPlainString(),
    timestamp = timestamp
)

fun Transaction.toDomain(owner: String): TransactionItem = TransactionItem(
    type = when (owner) {
        from -> {
            TransactionType.WITHDRAW
        }
        to -> {
            TransactionType.DEPOSIT
        }
        else -> throw IllegalArgumentException()
    },
    txId = txId.toLong(),
    from = from,
    to = to,
    value = Coin.fromCoin(value),
    timestamp = timestamp.toLong()
)