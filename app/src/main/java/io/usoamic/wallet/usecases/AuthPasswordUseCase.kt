package io.usoamic.wallet.usecases

import io.usoamic.wallet.domain.repositories.*
import javax.inject.Inject

class AuthPasswordUseCase @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mRealmRepository: RealmRepository,
    private val mValidateRepository: ValidateRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mUserRepository: UserRepository
) {

}