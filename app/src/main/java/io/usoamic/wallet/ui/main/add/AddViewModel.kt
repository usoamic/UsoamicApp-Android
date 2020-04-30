package io.usoamic.wallet.ui.main.add

import io.usoamic.wallet.extensions.observeOnMain
import io.usoamic.wallet.extensions.subscribeOnIo
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AddAccountUseCase
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val mModel: AddAccountUseCase
) : BaseViewModel() {
    init {

    }

    fun onAddClick(privateKey: String, password: String, confirmPassword: String) {
        mModel.addAccount(privateKey, password, confirmPassword)
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe({
                println(it)
            }, ::throwError)
    }
}