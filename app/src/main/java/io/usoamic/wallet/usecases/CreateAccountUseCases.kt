package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.wallet.domain.models.ethereum.AccountCredentials
import io.usoamic.wallet.domain.repositories.EthereumRepository
import javax.inject.Inject

class CreateAccountUseCases @Inject constructor(
    private val mEthereumRepository: EthereumRepository
) {
    fun createCredentials(): Single<AccountCredentials> = mEthereumRepository.createCredentials()
}