package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.realm.Realm
import javax.inject.Inject

class RealmRepositoryImpl @Inject constructor(
    private val realm: Realm
) : RealmRepository {
    override fun get(): Single<String> = Single.just(realm.version.toString())
}