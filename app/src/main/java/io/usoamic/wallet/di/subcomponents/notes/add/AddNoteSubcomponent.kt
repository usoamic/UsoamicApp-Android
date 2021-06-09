package io.usoamic.wallet.di.subcomponents.notes.add

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.wallet.ui.main.notes.add.AddNoteFragment

@AccountScope
@Subcomponent
interface AddNoteSubcomponent {
    fun inject(addNoteFragment: AddNoteFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddNoteSubcomponent
    }
}