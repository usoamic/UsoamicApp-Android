package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.wallet.domain.repositories.EthereumRepository
import io.usoamic.wallet.domain.repositories.PreferencesRepository
import io.usoamic.wallet.domain.repositories.UserRepository
import io.usoamic.wallet.domain.repositories.ValidateRepository
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import javax.inject.Inject

class UnlockUseCase @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mUserRepository: UserRepository,
    private val mPreferencesRepository: PreferencesRepository
) {
    fun getAddress(password: String): Single<String> {
        return mValidateRepository.validatePassword(password)
            .flatMap {
                mEthereumRepository.getAddress(password)
                    .map {
                        it
                    }
            }
    }

    fun saveData(address: String) {
        mPreferencesRepository.setAddress(address)
        mPreferencesRepository.setUnlockTime(LocalDateTime.now(ZoneOffset.UTC))
    }

    fun removePreferences() = mPreferencesRepository.removeAll()

    fun removeAccount() = mUserRepository.removeAccount()
}