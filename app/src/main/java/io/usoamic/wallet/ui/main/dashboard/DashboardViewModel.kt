package io.usoamic.wallet.ui.main.dashboard

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardItem
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardItemType
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : BaseViewModel() {
    val ldData = MutableLiveData<List<DashboardItem>>()
    val ldShowSwipeRefresh = MutableLiveData<Boolean>()

    init {
        ldData.value = listOf(
            DashboardItem(
                DashboardItemType.EthBalance,
                "111"
            ),
            DashboardItem(
                DashboardItemType.UsoBalance,
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

    fun onRefresh() {
        ldShowSwipeRefresh.value = false
    }
}