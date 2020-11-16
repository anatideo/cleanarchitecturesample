package com.picpay.desafio.android.features.contacts.data

import com.picpay.desafio.android.features.contacts.data.models.DataContact
import io.reactivex.Single

interface ContactsRepository {
    fun getContacts(): Single<List<DataContact>>
}