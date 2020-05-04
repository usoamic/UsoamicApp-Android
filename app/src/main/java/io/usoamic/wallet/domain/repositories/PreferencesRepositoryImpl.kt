package io.usoamic.wallet.domain.repositories

import android.content.Context
import android.content.SharedPreferences
import io.usoamic.wallet.UsoamicWallet
import io.usoamic.wallet.exceptions.PreferenceKeyNotFoundException
import io.usoamic.wallet.util.PreferenceKey
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor() : PreferencesRepository {
    private val preferences: SharedPreferences by lazy {
        UsoamicWallet.appContext.getSharedPreferences(PreferenceKey.SHARED_PREFS, Context.MODE_PRIVATE)
    }

    override fun getAddress(): String {
        val key = PreferenceKey.ADDRESS
        preferences.getString(key, null)?.let {
            return it
        } ?: run {
            throw PreferenceKeyNotFoundException(key)
        }
    }

    override fun setAddress(address: String) {
        with(preferences.edit()) {
            putString(PreferenceKey.ADDRESS, address)
            commit()
        }
    }
/*
    override fun setUnlockTime(timestamp: Long) {
        with(preferences.edit()) {
            putLong(PreferenceKey.TIMESTAMP, timestamp)
            commit()
        }
    }

 */
}