package io.usoamic.app.di.subcomponents.withdraw

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.app.ui.main.withdraw.WithdrawFragment

@AccountScope
@Subcomponent
interface WithdrawSubcomponent {
    fun inject(withdrawFragment: WithdrawFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): WithdrawSubcomponent
    }
}