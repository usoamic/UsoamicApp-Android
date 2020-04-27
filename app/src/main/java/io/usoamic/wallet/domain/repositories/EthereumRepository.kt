package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.usoamic.wallet.domain.models.ethereum.AccountCredentials

interface EthereumRepository {
    fun createCredentials(): Single<AccountCredentials>
}