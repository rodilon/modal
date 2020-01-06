package br.com.rodilon.testebancomodal.home.repository

import br.com.rodilon.testebancomodal.repository.Api
import br.com.rodilon.testebancomodal.repository.WebService

class RepositoryWebService {
    val api: Api = WebService().retrofitAuth.create(Api::class.java)

    companion object {
        private var INSTANCE: RepositoryWebService? = null

        fun getInstance(): RepositoryWebService {
            return INSTANCE
                ?: RepositoryWebService()
        }
    }
}