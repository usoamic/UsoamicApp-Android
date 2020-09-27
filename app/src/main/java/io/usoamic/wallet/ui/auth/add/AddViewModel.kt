package io.usoamic.wallet.ui.auth.add

import com.hadilq.liveevent.LiveEvent
import io.usoamic.wallet.extensions.addSchedulers

import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AddAccountUseCases
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val mModel: AddAccountUseCases
) : BaseViewModel() {
    val leAccountAdd = LiveEvent<Unit>()

    fun onAddClick(privateKey: String, password: String, confirmPassword: String) {
        mModel.addAccount(privateKey, password, confirmPassword)
            .addSchedulers()
            .addProgress()
            .subscribe({
                leAccountAdd.emit()
            }, ::throwError)
            .addToDisposable()
    }
}