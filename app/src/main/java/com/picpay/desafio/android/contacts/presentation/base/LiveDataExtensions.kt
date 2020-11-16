package com.picpay.desafio.android.contacts.presentation.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observeOn(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit
): LiveData<T> {
    observe(owner, Observer<T> { result -> result?.let { observer(result) } })
    return this
}
