package io.usoamic.wallet.ui.main.history

import androidx.lifecycle.MutableLiveData
import io.usoamic.wallet.domain.models.history.TransactionItem
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.ui.base.BaseSrViewModel
import io.usoamic.wallet.usecases.HistoryUseCase
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val mUseCase: HistoryUseCase
) : BaseSrViewModel() {
    val ldData = MutableLiveData<List<TransactionItem>>()

    init {
        updateTransactions()
    }

    private fun updateTransactions(force: Boolean = false) {
        mUseCase.getTransactions(force)
            .addSchedulers()
            .addProgress()
            .subscribe(::setHistory, ::throwError)
            .addToDisposable()
    }

    private fun setHistory(list: List<TransactionItem>) {
        ldData.value = list
    }

    override fun onRefresh() = updateTransactions(true)
}