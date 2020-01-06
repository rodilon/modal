package br.com.rodilon.testebancomodal.repository

import br.com.rodilon.testebancomodal.home.repository.RepositoryWebService
import br.com.rodilon.testebancomodal.home.model.RepositoryItemsList
import io.reactivex.Observable

class RepositoryHttp {

    private val webServiceManager: RepositoryWebService =
        RepositoryWebService.getInstance()

    fun getRepo(language: String, sort: String, page: Int): Observable<RepositoryItemsList> {
        return webServiceManager.api.getRepositories(language, sort, page)
    }
}