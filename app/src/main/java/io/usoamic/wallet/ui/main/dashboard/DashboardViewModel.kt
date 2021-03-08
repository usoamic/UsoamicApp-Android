package io.usoamic.wallet.ui.main.dashboard

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.dashboard.DashboardInfo
import io.usoamic.commons.crossplatform.usecases.DashboardUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.ui.base.BaseSrViewModel
import io.usoamic.wallet.ui.main.dashboard.adapter.DashboardItem
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mDashboardUseCases: DashboardUseCases
) : BaseSrViewModel() {
    val ldData = MutableLiveData<List<DashboardItem>>()

    init {
        updateInfo()
    }

    private fun updateInfo(force: Boolean = false) {
        mDashboardUseCases.getDashboardInfo(force)
            .addSchedulers()
            .addProgress(force)
            .subscribe(::onResult, ::throwError)
            .addToDisposable()
    }

    private fun onResult(data: DashboardInfo) {
        ldData.value = listOf(
            DashboardItem.UsoBalance(data.usoBalance),
            DashboardItem.EthBalance(data.ethBalance),
            DashboardItem.Height(data.height),
            DashboardItem.Supply(data.supply)
        )
    }

    override fun onRefresh() = updateInfo(true)
}