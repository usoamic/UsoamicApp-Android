package io.usoamic.app.ui.main.dashboard.adapter

import java.math.BigDecimal
import java.math.BigInteger

sealed class DashboardItem {
    data class EthBalance(val data: BigDecimal) : DashboardItem()
    data class UsoBalance(val data: BigDecimal) : DashboardItem()
    data class Height(val data: BigInteger) : DashboardItem()
    data class Supply(val data: BigDecimal) : DashboardItem()
}