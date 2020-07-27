package io.usoamic.wallet.domain.repositories

import io.usoamic.wallet.domain.models.realm.DashboardInfoRealm
import io.usoamic.wallet.domain.models.realm.TransactionItemRealm

interface RealmRepository {
    fun updateDashboardInfo(data: DashboardInfoRealm)
    fun addTransactionItem(data: TransactionItemRealm)

    fun getDashboardInfo(): DashboardInfoRealm?
    fun getTransactions(): List<TransactionItemRealm>
}