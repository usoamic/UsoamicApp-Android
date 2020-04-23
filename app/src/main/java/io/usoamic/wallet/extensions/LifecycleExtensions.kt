package io.usoamic.wallet.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> Fragment.observe(ld: MutableLiveData<T>, callback: (T) -> Unit) {
    ld.observe(viewLifecycleOwner, Observer<T> {
        callback.invoke(it)
    })
}