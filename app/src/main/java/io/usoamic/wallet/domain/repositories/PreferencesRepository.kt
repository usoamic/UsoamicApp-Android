package io.usoamic.wallet.domain.repositories

import org.threeten.bp.LocalDate

interface PreferencesRepository {
    fun getAddress(): String
    fun setAddress(address: String)
    //fun setUnlockTime(timestamp: LocalDate)
}