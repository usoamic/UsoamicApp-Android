package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun hasAccount(): Single<Boolean>
    fun getAddress(): Single<String>
}