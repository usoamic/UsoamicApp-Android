package io.usoamic.wallet.domain.models.dashboard

import io.usoamic.commons.crossplatform.models.dashboard.DashboardInfo
import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm
import java.math.BigDecimal
import java.math.BigInteger

fun DashboardInfoRealm.toDomain() = DashboardInfo(
    ethBalance = BigDecimal(ethBalance!!),
    usoBalance = BigDecimal(usoBalance!!),
    height = BigInteger(height!!),
    supply = BigDecimal(supply!!)
)

fun DashboardInfo.toRealm() = DashboardInfoRealm(
    ethBalance = ethBalance.toPlainString(),
    usoBalance = usoBalance.toPlainString(),
    height = height.toString(),
    supply = supply.toPlainString()
)