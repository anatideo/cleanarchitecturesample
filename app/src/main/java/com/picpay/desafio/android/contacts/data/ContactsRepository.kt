package com.picpay.desafio.android.contacts.data

import io.reactivex.Single

interface ContactsRepository {
    fun getContacts(): Single<List<DataContact>>
}