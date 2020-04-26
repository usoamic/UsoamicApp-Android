package io.usoamic.wallet.usecases

import io.usoamic.wallet.domain.repositories.RealmRepository
import io.usoamic.wallet.domain.repositories.TokenRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mRealmRepository: RealmRepository
) {

}