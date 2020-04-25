package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single

interface RealmRepository {
    fun get(): Single<String>
}