package io.usoamic.app.ui.main.history

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.commons.crossplatform.models.usecases.history.TransactionItem
import io.usoamic.app.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.HistoryUseCases
import io.usoamic.app.extensions.addSchedulers
import io.usoamic.app.ui.base.BaseSrViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val mUseCases: HistoryUseCases,
    mAppUseCases: AppUseCases
) : BaseSrViewModel(mAppUseCases, ScreenTag.WALLET) {
    val ldData = MutableLiveData<List<TransactionItem>>()

    init {
        updateTransactions()
    }

    private fun updateTransactions(force: Boolean = false) {
        mUseCases.getTransactions(force)
            .addSchedulers()
            .addProgress(force)
            .subscribe(::setHistory, ::throwError)
            .addToDisposable()
    }

    private fun setHistory(list: List<TransactionItem>) {
        ldData.value = list
    }

    override fun onRefresh() = updateTransactions(true)
}