package io.usoamic.wallet.di.subcomponents.unlock

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.AuthScope
import io.usoamic.wallet.ui.auth.unlock.UnlockWalletFragment

@AuthScope
@Subcomponent
interface UnlockWalletSubcomponent {
    fun inject(authFragment: UnlockWalletFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): UnlockWalletSubcomponent
    }
}