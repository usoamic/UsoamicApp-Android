package io.usoamic.wallet.domain.repositories

import android.content.Context
import android.content.SharedPreferences
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.exceptions.PreferenceKeyNotFoundException
import io.usoamic.wallet.util.PreferenceKey
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor() : PreferencesRepository {
    private val preferences: SharedPreferences by lazy {
        UsoamicWallet.appContext.getSharedPreferences(
            PreferenceKey.SHARED_PREFS,
            Context.MODE_PRIVATE
        )
    }

    override fun getAddress(): String = getString(PreferenceKey.ADDRESS)

    override fun setAddress(address: String) = setString(PreferenceKey.ADDRESS, address)

    override fun getUnlockTime(): LocalDateTime {
        val timestamp = getLong(PreferenceKey.TIMESTAMP)
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC)
    }

    override fun setUnlockTime(dateTime: LocalDateTime) = setLong(
        PreferenceKey.TIMESTAMP,
        dateTime.toEpochSecond(ZoneOffset.UTC)
    )

    override fun removeAll() {
        preferences.all.forEach {
            remove(it.key)
        }
    }

    private fun getString(key: String): String {
        preferences.getString(key, null)?.let {
            return it
        } ?: run {
            throw PreferenceKeyNotFoundException(key)
        }
    }

    private fun getLong(key: String): Long {
        val long = preferences.getLong(key, -1L)
        if (long == -1L) {
            throw PreferenceKeyNotFoundException(key)
        }
        return long
    }

    private fun setString(key: String, value: String) {
        with(preferences.edit()) {
            putString(key, value)
            commit()
        }
    }

    private fun setLong(key: String, value: Long) {
        with(preferences.edit()) {
            putLong(key, value)
            commit()
        }
    }

    override fun remove(key: String) {
        with(preferences.edit()) {
            remove(key)
            commit()
        }
    }
}