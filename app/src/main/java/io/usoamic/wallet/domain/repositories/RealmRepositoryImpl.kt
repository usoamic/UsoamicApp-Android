package io.usoamic.wallet.domain.repositories

import io.realm.Realm
import io.realm.RealmConfiguration
import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm
import javax.inject.Inject


class RealmRepositoryImpl @Inject constructor() : RealmRepository {
    private val realmConfig = RealmConfiguration.Builder()
        .name("realm")
        .schemaVersion(4)
        .deleteRealmIfMigrationNeeded()
        .build()

    private val realm: Realm get() = Realm.getInstance(realmConfig)

    override fun updateDashboardInfo(data: DashboardInfoRealm) {
        val realmData = DashboardInfoRealm(
            ethBalance = data.ethBalance,
            usoBalance = data.usoBalance,
            height = data.height,
            supply = data.supply
        )
        realm.executeTransaction {
            it.copyToRealmOrUpdate(realmData)
        }
    }

    override fun getDashboardInfo(): DashboardInfoRealm? {
        return realm.where(DashboardInfoRealm::class.java).findFirst()
    }
}