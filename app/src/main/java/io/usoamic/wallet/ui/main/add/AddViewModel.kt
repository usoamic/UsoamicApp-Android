package io.usoamic.wallet.ui.main.add

import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AddAccountUseCase
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val mModel: AddAccountUseCase
) : BaseViewModel() {
    init {

    }
}