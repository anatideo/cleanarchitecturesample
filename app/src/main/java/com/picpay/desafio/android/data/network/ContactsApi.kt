package com.picpay.desafio.android.data.network

import com.picpay.desafio.android.data.DataContact
import retrofit2.Call
import retrofit2.http.GET

interface ContactsApi {
    @GET("users")
    fun getContacts(): Call<List<DataContact>>
}