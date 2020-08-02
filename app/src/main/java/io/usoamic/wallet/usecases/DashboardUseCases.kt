package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.reactivex.functions.Function4
import io.usoamic.wallet.domain.models.dashboard.DashboardInfo
import io.usoamic.wallet.domain.models.dashboard.toDomain
import io.usoamic.wallet.domain.models.dashboard.toRealm
import io.usoamic.wallet.domain.repositories.EthereumRepository
import io.usoamic.wallet.domain.repositories.RealmRepository
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class DashboardUseCases @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mEthereumRepository: EthereumRepository,
    private val mRealmRepository: RealmRepository
) {
    fun getDashboardInfo(forceUpdate: Boolean): Single<DashboardInfo> {
        return if(forceUpdate) {
            getDashboardInfoFromNetwork()
        }
        else {
            getDashboardInfoFromRealm()
        }
    }

    private fun getDashboardInfoFromRealm(): Single<DashboardInfo> {
        return mRealmRepository.getDashboardInfo()?.let {
            Single.just(it.toDomain())
        } ?: getDashboardInfoFromNetwork()
    }

    private fun getDashboardInfoFromNetwork(): Single<DashboardInfo> {
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
            .map {
                mRealmRepository.updateDashboardInfo(it.toRealm())
                it
            }
    }
}