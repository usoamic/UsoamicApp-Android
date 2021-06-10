package io.usoamic.app.ui.main.dashboard

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.dashboard.DashboardModel
import io.usoamic.app.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.DashboardUseCases
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.ui.base.BaseSrViewModel
import io.usoamic.app.ui.main.dashboard.adapter.DashboardItem
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val mDashboardUseCases: DashboardUseCases,
    mAppUseCases: AppUseCases
) : BaseSrViewModel(mAppUseCases, ScreenTag.WALLET) {
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

    private fun onResult(data: DashboardModel) {
        ldData.value = listOf(
            DashboardItem.UsoBalance(data.usoBalance),
            DashboardItem.EthBalance(data.ethBalance),
            DashboardItem.Height(data.height),
            DashboardItem.Supply(data.supply)
        )
    }

    override fun onRefresh() = updateInfo(true)
}