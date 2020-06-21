package io.usoamic.wallet.domain.repositories

import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm

interface RealmRepository {
    fun updateDashboardInfo(data: DashboardInfoRealm)
    fun getDashboardInfo(): DashboardInfoRealm?
}