package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.wallet.domain.models.history.TransactionItem
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigInteger
import javax.inject.Inject

class HistoryUseCase @Inject constructor(
    private val mTokenRepository: TokenRepository
) {
    fun getTransactions(): Single<List<TransactionItem>> {
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
    }
}