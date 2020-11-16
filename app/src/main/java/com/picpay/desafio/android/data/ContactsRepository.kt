package com.picpay.desafio.android.data

import retrofit2.Call

interface ContactsRepository {
    fun getContacts(): Call<List<DataContact>>
}