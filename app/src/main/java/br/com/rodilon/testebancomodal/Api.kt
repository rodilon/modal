package br.com.rodilon.testebancomodal

import br.com.rodilon.testebancomodal.home.model.RepositoryItemsList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search/repositories?")
    fun getRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Observable<RepositoryItemsList>
}