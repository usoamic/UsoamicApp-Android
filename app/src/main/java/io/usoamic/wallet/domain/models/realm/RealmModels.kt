package io.usoamic.wallet.domain.models.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DashboardInfoRealm(
    @PrimaryKey var id: Long = 0,
    var ethBalance: String? = null,
    var usoBalance: String? = null,
    var height: String? = null,
    var supply: String? = null
) : RealmObject()

open class TransactionItemRealm(
    @PrimaryKey var txId: Long = 0,
    var type: String? = null,
    var from: String? = null,
    var to: String? = null,
    var value: String? = null,
    var timestamp: Long? = null
) : RealmObject()