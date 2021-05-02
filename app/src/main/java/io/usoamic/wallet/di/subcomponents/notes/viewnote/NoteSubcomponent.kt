package io.usoamic.wallet.di.subcomponents.notes.viewnote

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.wallet.ui.main.notes.viewnote.NoteFragment

@AccountScope
@Subcomponent
interface NoteSubcomponent {
    fun inject(noteFragment: NoteFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): NoteSubcomponent
    }
}