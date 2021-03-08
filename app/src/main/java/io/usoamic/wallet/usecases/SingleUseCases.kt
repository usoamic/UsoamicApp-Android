package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.commons.crossplatform.api.PreferencesCompat
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository
import io.usoamic.commons.crossplatform.repositories.api.UserRepository
import io.usoamic.wallet.BuildConfig
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.ChronoUnit
import javax.inject.Inject

class SingleUseCases @Inject constructor(
    private val mUserRepository: UserRepository,
    private val mPreferencesRepository: PreferencesRepository
) {
    fun hasAccount(): Single<Boolean> {
        return mUserRepository.hasAccount()
    }

    fun isNeedLocked(): Boolean {
        val timestamp = mPreferencesRepository.getUnlockTime()
        val unlockTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC)
        val currentTime = LocalDateTime.now(ZoneOffset.UTC)
        val between = ChronoUnit.SECONDS.between(unlockTime, currentTime)

        return (between >= BuildConfig.LOCK_SECONDS)
    }

    fun lockApp() {
        with(mPreferencesRepository) {
            remove(PreferencesCompat.Key.ADDRESS)
            remove(PreferencesCompat.Key.TIMESTAMP)
        }
    }
}