package io.usoamic.wallet.ui.main.dashboard

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardItem
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardItemType
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : BaseViewModel() {
    val ldData = MutableLiveData<List<DashboardItem>>()

    init {
        ldData.value = listOf(
            DashboardItem(
                DashboardItemType.UsoBalance,
                "111"
            ),
            DashboardItem(
                DashboardItemType.EthBalance,
                "222"
            ),
            DashboardItem(
                DashboardItemType.Height,
                "333"
            ),
            DashboardItem(
                DashboardItemType.Supply,
                "444"
            )

        )
    }
}