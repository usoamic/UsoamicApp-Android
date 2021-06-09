package io.usoamic.wallet.usecases

import io.usoamic.commons.crossplatform.api.DateCompat
import io.usoamic.commons.crossplatform.api.PreferencesCompat
import io.usoamic.commons.crossplatform.repositories.api.DbRepository
import io.usoamic.commons.crossplatform.repositories.api.PreferencesRepository
import io.usoamic.commons.crossplatform.repositories.api.UserRepository
import io.usoamic.commons.crossplatform.usecases.UserUseCases
import io.usoamic.wallet.BuildConfig
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.ChronoUnit
import javax.inject.Inject

class AppUseCases @Inject constructor(
    override val mUserRepository: UserRepository,
    override val mDatabaseRepository: DbRepository,
    override val mPreferencesRepository: PreferencesRepository,
    private val mDateCompat: DateCompat,
) : UserUseCases(
    mUserRepository = mUserRepository,
    mDatabaseRepository = mDatabaseRepository,
    mPreferencesRepository = mPreferencesRepository
) {
    fun updateLastActionTime() {
        if (!isNeedLocked()) {
            mPreferencesRepository.setLastActionTime(mDateCompat.currentTimestamp)
        }
    }

    fun isNeedLocked(): Boolean {
        val timestamp = getLastActionTime()
        val unlockTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC)
        val currentTime = LocalDateTime.now(ZoneOffset.UTC)
        val between = ChronoUnit.SECONDS.between(unlockTime, currentTime)

        return (between >= BuildConfig.LOCK_SECONDS)
    }

    fun lockApp() = with(mPreferencesRepository) {
        remove(PreferencesCompat.Key.ADDRESS)
        remove(PreferencesCompat.Key.LAST_ACTION_TIMESTAMP)
    }

    fun getLastActionTime(): Long = mPreferencesRepository.getLastActionTime()
}