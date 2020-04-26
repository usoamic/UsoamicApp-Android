package io.usoamic.wallet.di.subcomponents.add

import dagger.BindsInstance
import dagger.Subcomponent
import io.usoamic.wallet.di.scopes.AuthScope
import io.usoamic.wallet.domain.models.NavDirections
import io.usoamic.wallet.ui.main.add.AddFragment

@AuthScope
@Subcomponent
interface AddSubcomponent {
    fun inject(addFragment: AddFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance args: NavDirections.Add): AddSubcomponent
    }
}