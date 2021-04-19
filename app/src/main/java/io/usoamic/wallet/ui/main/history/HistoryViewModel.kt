package io.usoamic.wallet.ui.main.history

import androidx.lifecycle.MutableLiveData
import io.usoamic.commons.crossplatform.models.base.ScreenTag
import io.usoamic.commons.crossplatform.models.history.TransactionItem
import io.usoamic.wallet.usecases.AppUseCases
import io.usoamic.commons.crossplatform.usecases.HistoryUseCases
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.ui.base.BaseSrViewModel
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