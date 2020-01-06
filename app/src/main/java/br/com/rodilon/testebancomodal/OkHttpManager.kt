package br.com.rodilon.testebancomodal

import br.com.rodilon.testebancomodal.BuildConfig.DEBUG
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpManager : OkHttpClient() {

    private val TIME_OUT: Long = 60

    private var okHttpClient: OkHttpClient? = null

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        okHttpClient = Builder()
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .followRedirects(false)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}