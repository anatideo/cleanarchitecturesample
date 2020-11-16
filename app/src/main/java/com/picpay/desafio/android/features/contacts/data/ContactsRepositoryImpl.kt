package com.picpay.desafio.android.features.contacts.data

import com.picpay.desafio.android.features.contacts.data.models.DataContact
import com.picpay.desafio.android.features.contacts.data.network.ContactsApi
import io.reactivex.Single

class ContactsRepositoryImpl(
    private val contactsApi: ContactsApi
) : ContactsRepository {
    override fun getContacts(): Single<List<DataContact>> {
        return contactsApi.getContacts()
    }
}