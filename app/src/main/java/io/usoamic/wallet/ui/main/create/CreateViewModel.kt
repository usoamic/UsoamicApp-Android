package io.usoamic.wallet.ui.main.create

import io.usoamic.wallet.domain.models.AppArguments
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AddAccountUseCase
import io.usoamic.wallet.usecases.CreateAccountUseCase
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    private val mModel: CreateAccountUseCase,
    private val mArguments: AppArguments.Add
) : BaseViewModel() {
    init {

    }
}