package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.wallet.domain.models.history.TransactionItem
import io.usoamic.wallet.domain.models.history.toDomain
import io.usoamic.wallet.domain.models.history.toRealm
import io.usoamic.wallet.domain.repositories.RealmRepository
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigInteger
import javax.inject.Inject

class HistoryUseCases @Inject constructor(
    private val mTokenRepository: TokenRepository,
    private val mRealmRepository: RealmRepository
) {
    fun getTransactions(forceUpdate: Boolean): Single<List<TransactionItem>> {
        return if(forceUpdate) {
            getTransactionsFromNetwork()
        }
        else {
            getTransactionsFromRealm()
        }
            .map { items ->
                items.sortedByDescending {
                    it.timestamp
                }
            }
    }

    private fun getTransactionsFromRealm(): Single<List<TransactionItem>> {
        val items = mRealmRepository.getTransactions().map {
            it.toDomain()
        }
        return if(items.isEmpty()) {
            getTransactionsFromNetwork()
        }
        else {
            Single.just(items)
        }
    }

    private fun getTransactionsFromNetwork(): Single<List<TransactionItem>> {
        return mTokenRepository.numberOfUserTransactions
            .map { size ->
                val items = mutableListOf<TransactionItem>()
                var i = BigInteger.ZERO
                while (i < size) {
                    val tx = mTokenRepository.getTransactionForAccount(i).blockingGet()
                    items.add(tx)
                    i++
                }
                items.toList()
            }
            .map { items ->
                items.forEach {
                    mRealmRepository.addTransactionItem(it.toRealm())
                }
                items
            }
    }
}