package io.usoamic.app.di.subcomponents.history

import dagger.Subcomponent
import io.usoamic.commons.crossplatform.scopes.AccountScope
import io.usoamic.app.ui.main.history.HistoryFragment

@AccountScope
@Subcomponent
interface HistorySubcomponent {
    fun inject(historyFragment: HistoryFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HistorySubcomponent
    }
}