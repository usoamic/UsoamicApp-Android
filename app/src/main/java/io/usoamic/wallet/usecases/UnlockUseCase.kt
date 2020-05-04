package io.usoamic.wallet.usecases

import io.reactivex.rxjava3.core.Single
import io.usoamic.wallet.domain.repositories.EthereumRepository
import io.usoamic.wallet.domain.repositories.PreferencesRepository
import io.usoamic.wallet.domain.repositories.ValidateRepository
import javax.inject.Inject

class UnlockUseCase @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mPreferencesRepository: PreferencesRepository
) {
    fun saveAddress(password: String): Single<Boolean> {
        return mValidateRepository.validatePassword(password)
            .flatMap {
                mEthereumRepository.getAddress(password)
                    .map {
                        mPreferencesRepository.setAddress(it)
                        true
                    }
            }
    }
}