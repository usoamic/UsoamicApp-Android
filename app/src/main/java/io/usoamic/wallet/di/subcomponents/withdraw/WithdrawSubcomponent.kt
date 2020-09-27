package io.usoamic.wallet.di.subcomponents.withdraw

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.WalletScope
import io.usoamic.wallet.ui.main.withdraw.WithdrawFragment

@WalletScope
@Subcomponent
interface WithdrawSubcomponent {
    fun inject(withdrawFragment: WithdrawFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): WithdrawSubcomponent
    }
}