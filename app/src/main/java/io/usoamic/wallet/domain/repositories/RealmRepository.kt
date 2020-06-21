package io.usoamic.wallet.domain.repositories

import io.reactivex.Single
import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm

interface RealmRepository {
    fun get(): Single<String>
    fun updateDashboardInfo(data: DashboardInfoRealm)
    fun getDashboardInfo(): DashboardInfoRealm?
}