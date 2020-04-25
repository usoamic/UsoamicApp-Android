package io.usoamic.wallet.di.modules

import dagger.Binds
import dagger.Module
import io.usoamic.wallet.domain.repositories.RealmRepository
import io.usoamic.wallet.domain.repositories.RealmRepositoryImpl
import io.usoamic.wallet.domain.repositories.TokenRepository
import io.usoamic.wallet.domain.repositories.TokenRepositoryImpl

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindTokenRepository(repository: TokenRepositoryImpl): TokenRepository

    @Binds
    abstract fun bindRealmRepository(repository: RealmRepositoryImpl): RealmRepository
}