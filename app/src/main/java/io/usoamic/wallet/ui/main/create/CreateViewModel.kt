package io.usoamic.wallet.ui.main.create

import io.usoamic.wallet.domain.models.NavDirections
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.CreateAccountUseCase
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    private val mModel: CreateAccountUseCase,
    private val mArguments: NavDirections.Add
) : BaseViewModel() {
    init {

    }
}