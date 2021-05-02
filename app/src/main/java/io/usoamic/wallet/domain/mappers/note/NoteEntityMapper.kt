package io.usoamic.wallet.domain.mappers.note

import io.reactivex.functions.Function
import io.usoamic.commons.crossplatform.models.repository.notes.NoteEntity
import io.usoamic.wallet.domain.models.realm.NoteItemRealm
import org.web3j.crypto.Hash

class NoteEntityMapper(private val isAuthor: Boolean) : Function<NoteEntity, NoteItemRealm> {
    override fun apply(item: NoteEntity): NoteItemRealm {
        val id = item.noteId.toLong()
        val refId = item.noteRefId.toLong()

        return NoteItemRealm(
            hash = Hash.sha3("$id-$refId-$isAuthor"),
            id = id,
            noteType = item.noteType.toString(),
            refId = refId,
            content = item.content,
            author = item.author,
            timestamp = item.timestamp.toLong(),
            isAuthor = isAuthor
        )
    }
}