package io.usoamic.app.di.subcomponents.deposit

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.app.ui.main.deposit.DepositFragment

@AccountScope
@Subcomponent
interface DepositSubcomponent {
    fun inject(depositFragment: DepositFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): DepositSubcomponent
    }
}