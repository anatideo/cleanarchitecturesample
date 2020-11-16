package com.picpay.desafio.android.features.contacts.data.localsources

import com.picpay.desafio.android.features.contacts.data.models.DataContact
import io.reactivex.Single

interface ContactsLocalSource {
    fun saveContacts(list: List<DataContact>)
    fun getContacts(): Single<List<DataContact>>
}