package io.usoamic.wallet.ui.main.notes.viewnotes

import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.commons.crossplatform.usecases.NotesUseCases
import io.usoamic.validateutilkt.ValidateUtil
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.models.notes.ShowNoteInfo
import io.usoamic.wallet.ui.base.BaseSrViewModel
import io.usoamic.wallet.usecases.AppUseCases
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val mUseCases: NotesUseCases,
    mAppUseCases: AppUseCases
) : BaseSrViewModel(mAppUseCases, ScreenTag.WALLET) {
    val ldData = MutableLiveData<List<NoteItem>>()
    val leShowNote = LiveEvent<ShowNoteInfo>()

    init {
        updateNotes()
    }

    fun onFindNoteClick(refId: String) {
        Single.fromCallable {
            ValidateUtil.validateId(refId)
            refId.toLong()
        }
            .map(::mapToShowNoteInfo)
            .addSchedulers()
            .addProgress()
            .subscribe(leShowNote::emit, ::throwError)
            .addToDisposable()

    }

    private fun updateNotes(force: Boolean = false) {
        mUseCases.getNotes(force)
            .addSchedulers()
            .addProgress(force)
            .subscribe(ldData::setValue, ::throwError)
            .addToDisposable()
    }

    private fun mapToShowNoteInfo(id: Long): ShowNoteInfo {
        return ShowNoteInfo(id)
    }

    override fun onRefresh() = updateNotes(true)
}