package io.usoamic.wallet.usecases

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.usoamic.wallet.domain.models.add.AddAccountModel
import io.usoamic.wallet.domain.repositories.*
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mRealmRepository: RealmRepository,
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mUserRepository: UserRepository
) {
    fun addAccount(privateKey: String, password: String, confirmPassword: String): Single<AddAccountModel> {
        return Single.zip(
            mValidateRepository.validatePrivateKey(privateKey),
            mValidateRepository.validatePasswords(password, confirmPassword),
            BiFunction { pk: Boolean, p: Boolean ->
                pk && p
            }
        )
            .flatMap {
                mEthereumRepository.addAccount(privateKey, password)
            }
    }
}