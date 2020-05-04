package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.usoamic.validateutilkt.ValidateUtil
import io.usoamic.wallet.extensions.addDebugDelay
import java.util.concurrent.TimeUnit
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
}