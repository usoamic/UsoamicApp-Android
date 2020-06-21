package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.usoamic.wallet.domain.models.add.AddAccountModel
import io.usoamic.wallet.domain.models.ethereum.AccountCredentials
import java.math.BigDecimal
import java.math.BigInteger

interface EthereumRepository {
    fun createCredentials(): Single<AccountCredentials>
    fun addAccount(privateKey: String, password: String): Single<AddAccountModel>
    fun getAddress(password: String): Single<String>

    val ethBalance: Single<BigDecimal>
    val ethHeight: Single<BigInteger>
}