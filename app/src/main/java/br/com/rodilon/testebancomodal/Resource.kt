package br.com.rodilon.testebancomodal

class Resource<T> {
    var status: Int
        private set

    var data: T? = null
        private set

    constructor(status: Int, data: T) {
        this.status = status
        this.data = data
    }

    constructor(status: Int) {
        this.status = status
    }

    companion object {
        const val LOADING = 1111
        const val SUCCESS = 2222
        const val INTERNAL_SERVER_ERROR = 3333
    }
}