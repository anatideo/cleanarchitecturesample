package com.picpay.desafio.android.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetWorkHelper {
    inline fun <reified T> createApi(url: String): T {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .client(OkHttpClient.Builder().build())
            .build()
            .create(T::class.java)
    }
}