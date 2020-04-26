package io.usoamic.wallet.di.subcomponents.add

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.AuthScope
import io.usoamic.wallet.ui.main.add.AddFragment

@AuthScope
@Subcomponent
interface AddSubcomponent {
    fun inject(addFragment: AddFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddSubcomponent
    }
}