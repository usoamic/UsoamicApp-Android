package io.usoamic.wallet.usecases

import io.reactivex.Single
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.wallet.domain.repositories.TokenRepository
import java.math.BigInteger
import javax.inject.Inject

class HistoryUseCase @Inject constructor(
    private val mTokenRepository: TokenRepository
) {
    fun getTransactions(): Single<List<Transaction>> {
        return mTokenRepository.numberOfUserTransactions
            .map { size ->
                val items = mutableListOf<Transaction>()
                var i = BigInteger.ZERO
                while(i < size) {
                    val tx = mTokenRepository.getTransactionForAccount(i).blockingGet()
                    items.add(tx)
                    i++
                }
                items.toList()
            }
    }
}