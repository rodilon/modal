package br.com.rodilon.testebancomodal

import br.com.rodilon.testebancomodal.WebService.Companion.INTERNAL_SERVER_ERROR
import io.reactivex.Observer
import retrofit2.HttpException

abstract class CustomObserver<T> : Observer<T> {

    override fun onComplete() {
        onCompleteRequest()
    }

    override fun onNext(t: T) {
        onNextRequest(t)
    }

    override fun onError(throwable: Throwable) {
        if (throwable is HttpException) {
            if (throwable.code() == INTERNAL_SERVER_ERROR) {
                onInternalServerError(throwable)
                return
            }
        }
    }

    abstract fun onNextRequest(list: T)

    abstract fun onInternalServerError(throwable: Throwable?)

    abstract fun onCompleteRequest()
}