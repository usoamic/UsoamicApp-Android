package io.usoamic.wallet.ui.main.notes.add

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.usecases.NotesUseCases
import io.usoamic.usoamickt.enumcls.NoteType
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.usecases.AppUseCases

import io.usoamic.wallet.ui.base.BaseViewModel
import javax.inject.Inject

class AddNoteViewModel @Inject constructor(
    private val mUseCases: NotesUseCases,
    mAppUseCases: AppUseCases
) : BaseViewModel(mAppUseCases, ScreenTag.WALLET) {
    val leNoteAdded = LiveEvent<String>()

    fun onAddClick(password: String, content: String, noteType: String, txSpeed: String) {
        mUseCases.addNote(
            password = password,
            content = content,
            noteType = noteType,
            txSpeed = txSpeed
        )
            .addSchedulers()
            .addProgress()
            .subscribe(leNoteAdded::emit, ::throwError)
            .addToDisposable()
    }
}