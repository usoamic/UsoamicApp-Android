package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.usoamic.validateutilkt.ValidateUtil
import javax.inject.Inject

class ValidateRepositoryImpl @Inject constructor() : ValidateRepository {
    override fun validatePasswords(password: String, confirmPassword: String): Single<Boolean> {
        return Single.fromCallable {
            ValidateUtil.validatePasswords(password, confirmPassword)
            true
        }
    }

    override fun validatePrivateKey(privateKey: String): Single<Boolean> {
        return Single.fromCallable {
            ValidateUtil.validatePrivateKey(privateKey)
            true
        }
    }
}