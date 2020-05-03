package io.usoamic.wallet.di.subcomponents.unlock

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.AuthScope
import io.usoamic.wallet.ui.auth.unlock.UnlockFragment

@AuthScope
@Subcomponent
interface UnlockSubcomponent {
    fun inject(authFragment: UnlockFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): UnlockSubcomponent
    }
}