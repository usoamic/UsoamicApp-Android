package io.usoamic.wallet.di.subcomponents.notes

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.wallet.ui.main.notes.view.NotesFragment

@AccountScope
@Subcomponent
interface NotesSubcomponent {
    fun inject(notesFragment: NotesFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): NotesSubcomponent
    }
}