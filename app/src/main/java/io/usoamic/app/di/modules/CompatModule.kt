package io.usoamic.app.di.modules

import dagger.Binds
import dagger.Module
import io.usoamic.commons.crossplatform.api.DateCompat
import io.usoamic.commons.crossplatform.api.PreferencesCompat
import io.usoamic.app.domain.repositories.DateCompatImpl
import io.usoamic.app.domain.repositories.PreferencesCompatImpl

@Module
abstract class CompatModule {
    @Binds
    abstract fun bindPreferencesCompat(repository: PreferencesCompatImpl): PreferencesCompat

    @Binds
    abstract fun bindDateCompat(repository: DateCompatImpl): DateCompat
}