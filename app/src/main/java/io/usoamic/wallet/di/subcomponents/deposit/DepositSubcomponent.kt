package io.usoamic.wallet.di.subcomponents.deposit

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.WalletScope
import io.usoamic.wallet.ui.main.deposit.DepositFragment

@WalletScope
@Subcomponent
interface DepositSubcomponent {
    fun inject(depositFragment: DepositFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): DepositSubcomponent
    }
}