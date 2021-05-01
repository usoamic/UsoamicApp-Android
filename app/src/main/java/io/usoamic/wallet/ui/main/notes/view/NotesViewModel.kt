package io.usoamic.wallet.ui.main.notes.view

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.notes.NoteItem
import io.usoamic.commons.crossplatform.usecases.NotesUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.ui.base.BaseSrViewModel
import io.usoamic.wallet.usecases.AppUseCases
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val mUseCases: NotesUseCases,
    mAppUseCases: AppUseCases
) : BaseSrViewModel(mAppUseCases, ScreenTag.WALLET) {
    val ldData = MutableLiveData<List<NoteItem>>()

    init {
        updateTransactions()
    }

    private fun updateTransactions(force: Boolean = false) {
        mUseCases.getNotes(force)
            .addSchedulers()
            .addProgress(force)
            .subscribe(::setNotes, ::throwError)
            .addToDisposable()
    }

    private fun setNotes(list: List<NoteItem>) {
        ldData.value = list
    }

    override fun onRefresh() = updateTransactions(true)
}