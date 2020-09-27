package io.usoamic.wallet.di.subcomponents.auth

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.AuthScope
import io.usoamic.wallet.ui.auth.auth.AuthFragment

@AuthScope
@Subcomponent
interface AuthSubcomponent {
    fun inject(authFragment: AuthFragment)
}