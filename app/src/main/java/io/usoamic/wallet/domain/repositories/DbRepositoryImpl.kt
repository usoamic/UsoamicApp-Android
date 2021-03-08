package io.usoamic.wallet.domain.repositories

import io.realm.Realm
import io.realm.RealmConfiguration
import io.usoamic.commons.crossplatform.models.dashboard.DashboardInfo
import io.usoamic.commons.crossplatform.models.history.TransactionItem
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.wallet.domain.models.dashboard.toDomain
import io.usoamic.wallet.domain.models.dashboard.toRealm
import io.usoamic.wallet.domain.models.history.toDomain
import io.usoamic.wallet.domain.models.history.toRealm
import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm
import io.usoamic.wallet.domain.models.realm.TransactionItemRealm
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor() : DbRepository {
    private val realmConfig = RealmConfiguration.Builder()
        .name("realm")
        .schemaVersion(4)
        .deleteRealmIfMigrationNeeded()
        .build()

    private val realm: Realm get() = Realm.getInstance(realmConfig)

    override fun updateDashboardInfo(data: DashboardInfo) {
        realm.executeTransaction {
            it.copyToRealmOrUpdate(data.toRealm())
        }
    }

    override fun addTransactionItem(data: TransactionItem) {
        realm.executeTransaction {
            val transaction =
                it.where(TransactionItemRealm::class.java).equalTo("txId", data.txId).findFirst()
            if (transaction == null) {
                it.copyToRealm(data.toRealm())
            }
        }
    }

    override fun getTransactions(): List<TransactionItem> {
        return realm.where(TransactionItemRealm::class.java).findAll()
            .map {
                it.toDomain()
            }
            .toList()
    }

    override fun getDashboardInfo(): DashboardInfo? {
        return realm.where(DashboardInfoRealm::class.java).findFirst()?.toDomain()
    }
}