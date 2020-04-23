package io.usoamic.wallet.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val ldProgress = MutableLiveData<Boolean>()
    val ldThrowable = MutableLiveData<Throwable>()
    val ldError = MutableLiveData<String>()

}