package io.usoamic.wallet.di.modules

import dagger.Binds
import dagger.Module
import io.usoamic.wallet.domain.repositories.*

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindTokenRepository(repository: TokenRepositoryImpl): TokenRepository

    @Binds
    abstract fun bindRealmRepository(repository: RealmRepositoryImpl): RealmRepository

    @Binds
    abstract fun bindEthereumRepository(repository: EthereumRepositoryImpl): EthereumRepository

    @Binds
    abstract fun bindValidateRepository(repository: ValidateRepositoryImpl): ValidateRepository

    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindPreferencesRepository(repository: PreferencesRepositoryImpl): PreferencesRepository
}