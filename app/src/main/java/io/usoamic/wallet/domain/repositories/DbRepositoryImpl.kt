package io.usoamic.wallet.domain.repositories

import io.realm.Realm
import io.realm.RealmConfiguration
import io.usoamic.commons.crossplatform.models.repository.dashboard.DashboardEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.wallet.domain.models.dashboard.toEntity
import io.usoamic.wallet.domain.models.dashboard.toRealm
import io.usoamic.wallet.domain.models.history.toEntity
import io.usoamic.wallet.domain.models.history.toRealm
import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm
import io.usoamic.wallet.domain.models.realm.NoteItemRealm
import io.usoamic.wallet.domain.models.realm.TransactionItemRealm
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor() : DbRepository {
    private val realmConfig = RealmConfiguration.Builder()
        .name("realm")
        .schemaVersion(4)
        .deleteRealmIfMigrationNeeded()
        .build()

    private val realm: Realm get() = Realm.getInstance(realmConfig)

    override fun updateDashboardInfo(data: DashboardEntity) {
        realm.executeTransaction {
            it.copyToRealmOrUpdate(data.toRealm())
        }
    }

    override fun addTransactionItem(data: TransactionEntity) {
        realm.executeTransaction {
            val transaction =
                it.where(TransactionItemRealm::class.java).equalTo("txId", data.txId.toLong()).findFirst()
            if (transaction == null) {
                it.copyToRealm(data.toRealm())
            }
        }
    }

    override fun getTransactions(): List<TransactionEntity> {
        return realm.where(TransactionItemRealm::class.java).findAll()
            .map {
                it.toEntity()
            }
            .toList()
    }

    override fun getDashboardInfo(): DashboardEntity? {
        return realm.where(DashboardInfoRealm::class.java).findFirst()?.toEntity()
    }

    override fun addNote(data: NoteEntity) {
        realm.executeTransaction {
            val note =
                it.where(NoteItemRealm::class.java).equalTo("id", data.noteId.toLong()).findFirst()
            if (note == null) {
                it.copyToRealm(data.toRealm())
            }
        }
    }

    override fun getNotes(): List<NoteEntity> {
        return realm.where(NoteItemRealm::class.java).findAll()
            .map {
                it.toEntity()
            }
            .toList()
    }

    override fun removeAll() {
        realm.executeTransaction {
            it.deleteAll()
        }
    }
}