package com.picpay.desafio.android.contacts.data.network

import com.picpay.desafio.android.contacts.data.DataContact
import io.reactivex.Single
import retrofit2.http.GET

interface ContactsApi {
    @GET("users")
    fun getContacts(): Single<List<DataContact>>
}