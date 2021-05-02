package io.usoamic.wallet.di.subcomponents.notes.viewnote

import dagger.BindsInstance
import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.wallet.domain.models.AppArguments
import io.usoamic.wallet.ui.main.notes.viewnote.NoteFragment

@AccountScope
@Subcomponent
interface NoteSubcomponent {
    fun inject(noteFragment: NoteFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance args: AppArguments.Note): NoteSubcomponent
    }
}