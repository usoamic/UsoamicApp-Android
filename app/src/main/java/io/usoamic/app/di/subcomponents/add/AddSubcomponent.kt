package io.usoamic.app.di.subcomponents.add

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AuthScope
import io.usoamic.app.ui.auth.add.AddFragment

@AuthScope
@Subcomponent
interface AddSubcomponent {
    fun inject(addFragment: AddFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddSubcomponent
    }
}