package br.com.rodilon.testebancomodal.repository

import br.com.rodilon.testebancomodal.BuildConfig
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class WebService {

    companion object {
        const val INTERNAL_SERVER_ERROR = 500
    }

    var retrofitAuth: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
        .client(OkHttpManager())
        .build()
}