package br.com.rodilon.testebancomodal.home.domain

import androidx.lifecycle.MutableLiveData
import br.com.rodilon.testebancomodal.commom.CustomObserver
import br.com.rodilon.testebancomodal.model.Resource.Companion.LOADING
import br.com.rodilon.testebancomodal.model.Resource.Companion.SUCCESS
import br.com.rodilon.testebancomodal.home.model.RepositoryItemsList
import br.com.rodilon.testebancomodal.model.Resource
import br.com.rodilon.testebancomodal.repository.RepositoryHttp
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RepositoryInteractor(private var repositoryHttp: RepositoryHttp) {

    var mutableLiveData: MutableLiveData<Resource<RepositoryItemsList>> = MutableLiveData()

    private var repoObserver: Observer<RepositoryItemsList> = repoObserver()

    fun getRepo(language: String, sort: String, page: Int) {
        mutableLiveData.postValue(Resource(LOADING))
        repositoryHttp.getRepo(language, sort, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(repoObserver)
    }

    private fun repoObserver(): CustomObserver<RepositoryItemsList> {
        return object : CustomObserver<RepositoryItemsList>() {
            lateinit var repositoryItemsList: RepositoryItemsList

            override fun onNextRequest(list: RepositoryItemsList) {
                repositoryItemsList = list
            }

            override fun onInternalServerError(throwable: Throwable?) {}

            override fun onCompleteRequest() {
                mutableLiveData.postValue(
                    Resource(
                        SUCCESS,
                        repositoryItemsList
                    )
                )
            }

            override fun onSubscribe(d: Disposable) {}
        }
    }
}