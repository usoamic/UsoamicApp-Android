package io.usoamic.wallet.di

import dagger.Component
import io.usoamic.wallet.SingleActivity
import io.usoamic.wallet.di.modules.RealmModule
import io.usoamic.wallet.di.modules.RepositoryModule
import io.usoamic.wallet.di.modules.UsoamicModule
import io.usoamic.wallet.di.scopes.AppScope
import io.usoamic.wallet.di.subcomponents.add.AddSubcomponent
import io.usoamic.wallet.di.subcomponents.auth.AuthSubcomponent
import io.usoamic.wallet.di.subcomponents.create.CreateSubcomponent
import javax.inject.Singleton

@Singleton
@AppScope
@Component(modules = [UsoamicModule::class, RepositoryModule::class, RealmModule::class])
interface AppComponent {
    val authSubcomponent: AuthSubcomponent
    val addSubcomponent: AddSubcomponent.Factory
    val createSubcomponent: CreateSubcomponent.Factory

    fun inject(activity: SingleActivity)
}