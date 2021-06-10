package io.usoamic.app.ui.main.notes.add

import com.hadilq.liveevent.LiveEvent
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.usecases.NotesUseCases
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.extensions.emit
import io.usoamic.app.usecases.AppUseCases

import io.usoamic.app.ui.base.BaseViewModel
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