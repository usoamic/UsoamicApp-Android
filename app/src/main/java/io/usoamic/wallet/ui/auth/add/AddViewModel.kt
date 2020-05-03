package io.usoamic.wallet.ui.auth.add

import com.hadilq.liveevent.LiveEvent
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.extensions.observeOnMain
import io.usoamic.wallet.extensions.subscribeOnIo
import io.usoamic.wallet.ui.base.BaseViewModel
import io.usoamic.wallet.usecases.AddAccountUseCase
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val mModel: AddAccountUseCase
) : BaseViewModel() {
    val leAccountAdd = LiveEvent<Unit>()
    init {

    }

    fun onAddClick(privateKey: String, password: String, confirmPassword: String) {
        mModel.addAccount(privateKey, password, confirmPassword)
            .subscribeOnIo()
            .observeOnMain()
            .addProgress()
            .subscribe({
                leAccountAdd.emit()
            }, ::throwError)
    }
}