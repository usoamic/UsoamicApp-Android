package io.usoamic.app.di.subcomponents.auth

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AuthScope
import io.usoamic.app.ui.auth.auth.AuthFragment

@AuthScope
@Subcomponent
interface AuthSubcomponent {
    fun inject(authFragment: AuthFragment)
}