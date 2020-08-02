package io.usoamic.wallet.domain.models.withdraw

import io.usoamic.usoamickt.enumcls.TxSpeed
import java.math.BigInteger

data class WithdrawData(
    val coin: WithdrawCoin,
    val password: String,
    val to: String,
    val value: BigInteger,
    val txSpeed: TxSpeed
)

enum class WithdrawCoin {
    ETH,
    USO
}