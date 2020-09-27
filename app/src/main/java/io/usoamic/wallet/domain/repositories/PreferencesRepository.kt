package io.usoamic.wallet.domain.repositories

import org.threeten.bp.LocalDateTime

interface PreferencesRepository {
    fun getAddress(): String
    fun setAddress(address: String)
    fun getUnlockTime(): LocalDateTime
    fun setUnlockTime(dateTime: LocalDateTime)
    fun remove(key: String)
    fun removeAll()
}