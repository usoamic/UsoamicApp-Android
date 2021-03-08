package io.usoamic.wallet.di.subcomponents.withdraw

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.wallet.ui.main.withdraw.WithdrawFragment

@AccountScope
@Subcomponent
interface WithdrawSubcomponent {
    fun inject(withdrawFragment: WithdrawFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): WithdrawSubcomponent
    }
}