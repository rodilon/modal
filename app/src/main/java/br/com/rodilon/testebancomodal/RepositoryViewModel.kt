package br.com.rodilon.testebancomodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.rodilon.testebancomodal.home.model.RepositoryItemsList

class RepositoryViewModel : ViewModel() {

    var repositoryLiveData: LiveData<Resource<RepositoryItemsList>>
    private val repositoryInteractor: RepositoryInteractor = RepositoryInteractor(RepositoryHttp())

    init {
        repositoryLiveData = repositoryInteractor.mutableLiveData
    }

    fun getRepo(language: String, sort: String, page: Int) {
        repositoryInteractor.getRepo(language, sort, page)
    }
}