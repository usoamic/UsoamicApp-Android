package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.usoamic.usoamickt.core.Usoamic
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val usoamic: Usoamic) : UserRepository {
    override fun hasAccount(): Single<Boolean> {
        return Single.fromCallable {
            usoamic.hasAccount
        }
    }

    override fun getAddress(): Single<String> {
        return Single.fromCallable {
            usoamic.address
        }
    }
}