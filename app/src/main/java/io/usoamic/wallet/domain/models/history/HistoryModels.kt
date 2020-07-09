package io.usoamic.wallet.domain.models.history

import io.usoamic.usoamickt.util.Coin
import io.usoamic.wallet.domain.models.realm.TransactionItemRealm
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

data class TransactionItem(
    val txId: Long,
    val from: String,
    val to: String,
    val value: Coin,
    val date: LocalDateTime
)

fun TransactionItemRealm.toRealm(): TransactionItem = TransactionItem(
    txId = txId,
    from = from,
    to = to,
    value = Coin.fromCoin(value),
    date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault())
)