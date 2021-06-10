package io.usoamic.app.domain.repositories

import io.realm.Realm
import io.realm.RealmConfiguration
import io.usoamic.commons.crossplatform.models.repository.dashboard.DashboardEntity
import io.usoamic.commons.crossplatform.models.repository.history.TransactionEntity
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.app.domain.mappers.dashboard.toEntity
import io.usoamic.app.domain.mappers.dashboard.toRealm
import io.usoamic.app.domain.mappers.history.toEntity
import io.usoamic.app.domain.mappers.history.toRealm
import io.usoamic.app.domain.mappers.note.NoteEntityMapper
import io.usoamic.app.domain.mappers.note.toEntity
import io.usoamic.app.domain.models.realm.DashboardInfoRealm
import io.usoamic.app.domain.models.realm.NoteItemRealm
import io.usoamic.app.domain.models.realm.TransactionItemRealm
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

    override fun addNoteForAccount(data: NoteEntity) = addNote(
        data = data,
        isAuthor = true
    )

    override fun addNote(data: NoteEntity) = addNote(
        data = data,
        isAuthor = false
    )

    override fun getNotes(): List<NoteEntity> {
        return realm.where(NoteItemRealm::class.java)
            .equalTo("isAuthor", true)
            .findAll()
            .map {
                it.toEntity()
            }
            .toList()
    }

    override fun getNote(refId: Long): NoteEntity? {
        return getNote(refId, false)
    }

    override fun getNoteForAccount(id: Long): NoteEntity? {
        return getNote(id, true)
    }


    override fun removeAll() {
        realm.executeTransaction {
            it.deleteAll()
        }
    }

    private fun getNote(id: Long, forAccount: Boolean): NoteEntity? {
        val idKey = if (forAccount) {
            "id"
        } else {
            "refId"
        }
        return realm.where(NoteItemRealm::class.java)
            .equalTo(idKey, id)
            .findFirst()?.toEntity()
    }

    private fun addNote(data: NoteEntity, isAuthor: Boolean) {
        realm.executeTransaction {
            val (idKey, id) = if (isAuthor) {
                "id" to data.noteId
            } else {
                "refId" to data.noteId
            }

            val note =
                it.where(NoteItemRealm::class.java)
                    .equalTo(idKey, id.toLong())
                    .equalTo("isAuthor", isAuthor)
                    .findFirst()
            if (note == null) {
                it.copyToRealm(
                    NoteEntityMapper(isAuthor).apply(data)
                )
            }
        }
    }
}