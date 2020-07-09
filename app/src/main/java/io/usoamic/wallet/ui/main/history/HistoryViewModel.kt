package io.usoamic.wallet.ui.main.history

import io.usoamic.wallet.ui.base.BaseSrViewModel
import io.usoamic.wallet.usecases.HistoryUseCase
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val mUseCase: HistoryUseCase
) : BaseSrViewModel() {
    init {

    }

}