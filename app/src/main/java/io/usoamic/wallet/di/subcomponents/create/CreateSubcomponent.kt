package io.usoamic.wallet.di.subcomponents.create

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.AuthScope
import io.usoamic.wallet.ui.main.create.CreateFragment

@AuthScope
@Subcomponent
interface CreateSubcomponent {
    fun inject(addFragment: CreateFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateSubcomponent
    }
}