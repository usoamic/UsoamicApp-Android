package io.usoamic.wallet.domain.repositories

import io.reactivex.Single
import io.realm.Realm
import io.realm.kotlin.where
import io.usoamic.wallet.domain.models.dashboard.DashboardInfo
import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm
import io.usoamic.wallet.extensions.addDebugDelay
import javax.inject.Inject

class RealmRepositoryImpl @Inject constructor(
    private val realm: Realm
) : RealmRepository {
    @Deprecated("Test method") override fun get(): Single<String> = Single.just(realm.version.toString()).addDebugDelay()

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