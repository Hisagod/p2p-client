package com.aib.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxObserver<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {

    }
}