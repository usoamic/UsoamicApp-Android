package io.usoamic.wallet.di.subcomponents.history

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.wallet.ui.main.history.HistoryFragment

@AccountScope
@Subcomponent
interface HistorySubcomponent {
    fun inject(historyFragment: HistoryFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HistorySubcomponent
    }
}