package io.usoamic.wallet.usecases

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function4
import io.usoamic.wallet.domain.models.dashboard.DashboardInfo
import io.usoamic.wallet.domain.repositories.EthereumRepository
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mEthereumRepository: EthereumRepository
) {

    fun getDashboardInfoFromRealm(): Single<DashboardInfo> {
        TODO()
    }

    fun getDashboardInfoFromNetwork(): Single<DashboardInfo> {
        return Single.zip(
            mEthereumRepository.ethBalance,
            mTokenRepository.usoBalance,
            mEthereumRepository.ethHeight,
            mTokenRepository.usoSupply,
            Function4 { ethBalance: BigDecimal, usoBalance: BigDecimal, ethHeight: BigInteger, usoSupply: BigDecimal ->
                DashboardInfo(
                    ethBalance,
                    usoBalance,
                    ethHeight,
                    usoSupply
                )
            }
        )
    }
}