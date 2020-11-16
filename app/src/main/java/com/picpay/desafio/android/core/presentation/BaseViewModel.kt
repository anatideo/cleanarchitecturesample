package com.picpay.desafio.android.core.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    private val onClearedDisposeBag = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        onClearedDisposeBag.clear()
    }

    fun Disposable.disposeOnCleared(): Disposable {
        onClearedDisposeBag.add(this)
        return this
    }

    /**
     * Subscribes on ui thread.
     */
    inline fun <T : Any> Single<T>.subscribeOnUi(
        crossinline onSubscribe: () -> Unit = {},
        crossinline afterTerminate: () -> Unit = {},
        crossinline onError: (Throwable) -> Boolean = { false },
        crossinline onSuccess: (T) -> Unit = {}
    ): Disposable {
        return observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { onSubscribe() }
            .doAfterTerminate { afterTerminate() }
            .subscribeBy(
                onSuccess = { result: T -> onSuccess(result) },
                onError = { error ->
                    if (!onError(error)) {
                        error.printStackTrace()
                    }
                }
            )
            .disposeOnCleared()
    }
}
