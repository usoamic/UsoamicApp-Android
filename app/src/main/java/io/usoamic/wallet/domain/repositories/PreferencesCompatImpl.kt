package io.usoamic.wallet.domain.repositories

import android.content.Context
import android.content.SharedPreferences
import io.usoamic.commons.crossplatform.api.PreferencesCompat
import io.usoamic.commons.crossplatform.exceptions.PreferenceKeyNotFoundThrowable
import io.usoamic.wallet.UsoamicWallet
import javax.inject.Inject

class PreferencesCompatImpl @Inject constructor() : PreferencesCompat {
    private val preferences: SharedPreferences by lazy {
        UsoamicWallet.appContext.getSharedPreferences(
                PreferencesCompat.Key.SHARED_PREFS,
                Context.MODE_PRIVATE
        )
    }

    override fun getString(key: String): String {
        preferences.getString(key, null)?.let {
            return it
        } ?: run {
            throw PreferenceKeyNotFoundThrowable(key)
        }
    }

    override fun getLong(key: String): Long {
        val long = preferences.getLong(key, -1L)
        if (long == -1L) {
            throw PreferenceKeyNotFoundThrowable(key)
        }
        return long
    }

    override fun putString(key: String, value: String) {
        with(preferences.edit()) {
            putString(key, value)
            commit()
        }
    }

    override fun putLong(key: String, value: Long) {
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

    override fun removeAll() {
        preferences.all.forEach {
            remove(it.key)
        }
    }
}