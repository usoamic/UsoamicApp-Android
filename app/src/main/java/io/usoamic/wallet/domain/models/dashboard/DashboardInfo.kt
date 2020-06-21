package io.usoamic.wallet.domain.models.dashboard

import java.math.BigDecimal
import java.math.BigInteger

data class DashboardInfo(
    val ethBalance: BigDecimal,
    val usoBalance: BigDecimal,
    val height: BigInteger,
    val supply: BigDecimal
)