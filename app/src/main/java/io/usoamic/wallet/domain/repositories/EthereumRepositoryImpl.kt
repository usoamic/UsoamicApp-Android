package io.usoamic.wallet.domain.repositories

import io.reactivex.rxjava3.core.Single
import io.usoamic.usoamickt.core.Usoamic
import io.usoamic.wallet.domain.models.ethereum.AccountCredentials
import io.usoamic.wallet.domain.models.ethereum.toDomain
import io.usoamic.wallet.extensions.privateKey
import io.usoamic.wallet.extensions.subscribeOnIo
import org.web3j.crypto.Credentials
import org.web3j.crypto.Keys
import org.web3j.crypto.WalletUtils
import javax.inject.Inject

class EthereumRepositoryImpl @Inject constructor(
    private val usoamic: Usoamic
) : EthereumRepository {
    override fun createCredentials(): Single<AccountCredentials> {
        return Single.fromCallable {
            var credentials: Credentials
            do {
                credentials = Credentials.create(Keys.createEcKeyPair())
            } while (!WalletUtils.isValidPrivateKey(credentials.privateKey))
            credentials.toDomain()
        }
            .subscribeOnIo()
    }
}