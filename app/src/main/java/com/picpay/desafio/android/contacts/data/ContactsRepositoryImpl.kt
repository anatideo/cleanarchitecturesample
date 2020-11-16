package com.picpay.desafio.android.contacts.data

import com.picpay.desafio.android.contacts.data.network.ContactsApi
import io.reactivex.Single

class ContactsRepositoryImpl(
    private val contactsApi: ContactsApi
) : ContactsRepository {
    override fun getContacts(): Single<List<DataContact>> {
        return contactsApi.getContacts()
    }
}