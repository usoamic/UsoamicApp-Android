package io.usoamic.app.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent

fun <T> Fragment.observe(ld: MutableLiveData<T>, callback: (T) -> Unit) {
    viewLifecycleOwner.observe(ld, callback)
}

fun <T> LifecycleOwner.observe(ld: MutableLiveData<T>, callback: (T) -> Unit) {
    ld.observe(this, {
        callback.invoke(it)
    })
}

fun LiveEvent<Unit>.emit() {
    value = Unit
}

fun <T> LiveEvent<T>.emit(arg: T) {
    value = arg
}