package com.picpay.desafio.android.data.network

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.DataContact
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PicPayServiceImpl(
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
) : PicPayService {
    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    override fun getContacts(): Call<List<DataContact>> {
        return service.getContacts()
    }

    companion object {
        private const val BASE_URL = "http://careers.picpay.com/tests/mobdev/"
    }
}