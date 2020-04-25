package io.usoamic.wallet.usecases

import io.reactivex.rxjava3.core.Single
import io.usoamic.wallet.domain.repositories.RealmRepository
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigDecimal
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mRealmRepository: RealmRepository
) {
    fun getSupply(): Single<BigDecimal> = mTokenRepository.getSupply()

    fun getVersion(): Single<String> = mRealmRepository.get()
}