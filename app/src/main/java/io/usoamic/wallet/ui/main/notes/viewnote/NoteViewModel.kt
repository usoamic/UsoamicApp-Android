package io.usoamic.wallet.ui.main.notes.viewnote

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.Singles
import io.usoamic.commons.crossplatform.extensions.toCleanedHexPrefixString
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.commons.crossplatform.repositories.api.UserRepository
import io.usoamic.commons.crossplatform.usecases.NotesUseCases
import io.usoamic.wallet.domain.models.AppArguments
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.toLocalDateTime
import io.usoamic.wallet.models.NoteInfo
import io.usoamic.wallet.ui.base.BaseSrViewModel
import io.usoamic.wallet.usecases.AppUseCases
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val mUseCases: NotesUseCases,
    private val mUserRepository: UserRepository,
    private val mArguments: AppArguments.Note,
    mAppUseCases: AppUseCases
) : BaseSrViewModel(mAppUseCases, ScreenTag.WALLET) {
    val ldData = MutableLiveData<NoteInfo>()

    init {
        refreshNote()
    }

    private fun refreshNote(force: Boolean = false) {
        Singles.zip(
            mUseCases.getNote(mArguments.refId, force),
            mUserRepository.getAddress()
        ) { note, address ->
            mapToNoteInfo(note, address)
        }

            .addSchedulers()
            .addProgress()
            .subscribe(ldData::setValue, ::throwError)
            .addToDisposable()
    }

    private fun mapToNoteInfo(item: NoteItem, address: String): NoteInfo {
        return NoteInfo(
            refId = item.refId,
            type = item.type,
            content = item.content,
            author = item.author,
            date = item.timestamp.toLocalDateTime(),
            isOwn = item.author.toCleanedHexPrefixString() == address.toCleanedHexPrefixString()
        )
    }

    override fun onRefresh() = refreshNote(true)
}