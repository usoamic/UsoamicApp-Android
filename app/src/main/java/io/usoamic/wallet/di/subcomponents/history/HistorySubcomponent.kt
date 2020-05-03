package io.usoamic.wallet.di.subcomponents.history

import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.WalletScope
import io.usoamic.wallet.ui.main.history.HistoryFragment

@WalletScope
@Subcomponent
interface HistorySubcomponent {
    fun inject(historyFragment: HistoryFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HistorySubcomponent
    }
}