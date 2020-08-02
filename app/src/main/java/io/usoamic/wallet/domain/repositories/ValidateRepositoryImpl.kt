package io.usoamic.wallet.domain.repositories

import io.reactivex.Single
import io.usoamic.validateutilkt.ValidateUtil
import io.usoamic.wallet.extensions.addDebugDelay
import javax.inject.Inject

class ValidateRepositoryImpl @Inject constructor() : ValidateRepository {
    override fun validatePasswords(password: String, confirmPassword: String): Single<Boolean> {
        return Single.fromCallable {
            ValidateUtil.validatePasswords(password, confirmPassword)
            true
        }

            .addDebugDelay()
    }

    override fun validatePassword(password: String): Single<Boolean> {
        return Single.fromCallable {
            ValidateUtil.validatePassword(password)
            true
        }

            .addDebugDelay()
    }

    override fun validatePrivateKey(privateKey: String): Single<Boolean> {
        return Single.fromCallable {
            ValidateUtil.validatePrivateKey(privateKey)
            true
        }

            .addDebugDelay()
    }

    override fun validateTransferValue(value: String): Single<Boolean> {
        return Single.fromCallable {
            ValidateUtil.validateTransferValue(value)
            true
        }.addDebugDelay()
    }

    override fun validateAddress(address: String): Single<Boolean> {
        return Single.fromCallable {
            ValidateUtil.validateAddress(address)
            true
        }.addDebugDelay()
    }
}