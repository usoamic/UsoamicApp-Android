package io.usoamic.app.ui.main.notes.viewnote

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.commons.crossplatform.usecases.NotesUseCases
import io.usoamic.usoamickt.enumcls.NoteType
import io.usoamic.app.domain.models.AppArguments
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.ui.base.BaseSrViewModel
import io.usoamic.app.usecases.AppUseCases
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val mUseCases: NotesUseCases,
    private val mArguments: AppArguments.Note,
    mAppUseCases: AppUseCases
) : BaseSrViewModel(mAppUseCases, ScreenTag.WALLET) {
    private val noteId: Long get() = mArguments.id

    val ldData = MutableLiveData<NoteItem>()

    init {
        refreshNote(force = false)
    }

    private fun refreshNote(force: Boolean = false) {
        getNote(force)
            .addSchedulers()
            .addProgress(force)
            .subscribe(ldData::setValue, ::throwErrorAndFinish)
            .addToDisposable()
    }

    override fun onRefresh() = refreshNote(true)

    private fun getNote(force: Boolean = false): Single<NoteItem> {
        return when (mArguments.noteType) {
            NoteType.PUBLIC -> mUseCases.getNote(noteId, force)
            NoteType.UNLISTED -> mUseCases.getNoteForAccount(noteId, force)
        }
    }
}