package com.picpay.desafio.android.features.contacts.data.network

import com.picpay.desafio.android.features.contacts.data.models.DataContact
import io.reactivex.Single
import retrofit2.http.GET

interface ContactsApi {
    @GET("users")
    fun getContacts(): Single<List<DataContact>>
}