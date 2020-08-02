package io.usoamic.wallet.usecases

import io.usoamic.wallet.BuildConfig
import io.usoamic.wallet.domain.repositories.PreferencesRepository
import io.usoamic.wallet.util.PreferenceKey
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.ChronoUnit
import javax.inject.Inject

class SingleUseCases @Inject constructor(
    private val mPreferencesRepository: PreferencesRepository
) {
    fun isNeedLocked(): Boolean {
        val unlockTime = mPreferencesRepository.getUnlockTime()
        val currentTime = LocalDateTime.now(ZoneOffset.UTC)
        val between = ChronoUnit.SECONDS.between(unlockTime, currentTime)
        return (between >= BuildConfig.LOCK_SECONDS)
    }

    fun lockApp() {
        mPreferencesRepository.apply {
            remove(PreferenceKey.ADDRESS)
            remove(PreferenceKey.TIMESTAMP)
        }
    }
}