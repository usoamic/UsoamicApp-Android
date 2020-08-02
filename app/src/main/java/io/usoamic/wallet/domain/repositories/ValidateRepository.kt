package io.usoamic.wallet.domain.repositories

import io.reactivex.Single

interface ValidateRepository {
    fun validatePasswords(password: String, confirmPassword: String): Single<Boolean>
    fun validatePassword(password: String): Single<Boolean>
    fun validatePrivateKey(privateKey: String): Single<Boolean>
    fun validateAddress(address: String): Single<Boolean>
    fun validateTransferValue(value: String): Single<Boolean>
}