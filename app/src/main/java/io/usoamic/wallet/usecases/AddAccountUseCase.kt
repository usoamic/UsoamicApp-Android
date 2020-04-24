package io.usoamic.wallet.usecases

import io.reactivex.rxjava3.core.Single
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigDecimal
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val tokenRepository: TokenRepository
) {
    fun getSupply(): Single<BigDecimal> = tokenRepository.getSupply()
}