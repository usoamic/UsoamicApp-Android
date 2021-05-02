package io.usoamic.wallet.domain.repositories

import io.realm.Realm
import io.realm.RealmConfiguration
import io.usoamic.commons.crossplatform.models.repository.dashboard.DashboardEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.wallet.domain.mappers.dashboard.toEntity
import io.usoamic.wallet.domain.mappers.dashboard.toRealm
import io.usoamic.wallet.domain.mappers.history.toEntity
import io.usoamic.wallet.domain.mappers.history.toRealm
import io.usoamic.wallet.domain.mappers.note.NoteEntityMapper
import io.usoamic.wallet.domain.mappers.note.toEntity
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
                it.where(TransactionItemRealm::class.java)
                    .equalTo("txId", data.txId.toLong())
                    .findFirst()

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
        return realm.where(DashboardInfoRealm::class.java)
            .findFirst()?.toEntity()
    }

    override fun addOwnNote(data: NoteEntity) = addNote(
        data = data,
        isOwnNote = true
    )

    override fun addNote(data: NoteEntity) = addNote(
        data = data,
        isOwnNote = false
    )

    override fun getNotes(): List<NoteEntity> {
        return realm.where(NoteItemRealm::class.java)
            .equalTo("isOwnNote", true)
            .findAll()
            .map {
                it.toEntity()
            }
            .toList()
    }

    override fun getNote(refId: Long): NoteEntity? {
        return realm.where(NoteItemRealm::class.java)
            .equalTo("isOwnNote", false)
            .findFirst()?.toEntity()
    }


    override fun removeAll() {
        realm.executeTransaction {
            it.deleteAll()
        }
    }

    private fun addNote(data: NoteEntity, isOwnNote: Boolean) {
        realm.executeTransaction {
            val note =
                it.where(NoteItemRealm::class.java).equalTo("id", data.noteId.toLong()).findFirst()
            if (note == null) {
                it.copyToRealm(
                    NoteEntityMapper(isOwnNote).apply(data)
                )
            }
        }
    }
}