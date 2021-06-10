package io.usoamic.app.di.subcomponents.create

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AuthScope
import io.usoamic.app.ui.auth.create.CreateFragment

@AuthScope
@Subcomponent
interface CreateSubcomponent {
    fun inject(addFragment: CreateFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateSubcomponent
    }
}