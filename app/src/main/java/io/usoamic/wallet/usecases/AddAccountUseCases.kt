package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.wallet.domain.models.add.AddAccountModel
import io.usoamic.wallet.domain.repositories.EthereumRepository
import io.usoamic.wallet.domain.repositories.ValidateRepository
import javax.inject.Inject

class AddAccountUseCases @Inject constructor(
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository
) {
    fun addAccount(
        privateKey: String,
        password: String,
        confirmPassword: String
    ): Single<AddAccountModel> {
        return mValidateRepository.validatePrivateKey(privateKey)
            .andThen(
                mValidateRepository.validatePasswords(password, confirmPassword)
            )
            .andThen(
                mEthereumRepository.addAccount(privateKey, password)
            )
    }
}