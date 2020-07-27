package io.usoamic.wallet.ui.main.history

import androidx.lifecycle.MutableLiveData
import io.usoamic.usoamickt.model.Transaction
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.ui.base.BaseSrViewModel
import io.usoamic.wallet.usecases.HistoryUseCase
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val mUseCase: HistoryUseCase
) : BaseSrViewModel() {
    val ldHistory = MutableLiveData<List<Transaction>>()

    init {
        getTransactions()
    }

    private fun getTransactions() {
        mUseCase.getTransactions()
            .addSchedulers()
            .addProgress()
            .subscribe(::setHistory, ::throwError)
            .addToDisposable()
    }

    private fun setHistory(list: List<Transaction>) {
        ldHistory.value = list
    }
}