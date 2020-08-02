package io.usoamic.wallet.ui.main.withdraw

import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.WithdrawUseCases
import javax.inject.Inject

class WithdrawViewModel @Inject constructor(
    private val mUseCases: WithdrawUseCases
) : BaseViewModel() {
    init {

    }
}