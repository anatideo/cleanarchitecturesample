package com.picpay.desafio.android.features.contacts.data.repositories

import com.picpay.desafio.android.features.contacts.data.mappers.ContactMapper
import com.picpay.desafio.android.features.contacts.data.network.ContactsApi
import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.domain.repositories.ContactsRepository
import io.reactivex.Single

class ContactsRepositoryImpl(
    private val contactsApi: ContactsApi,
    private val contactMapper: ContactMapper
) : ContactsRepository {

    override fun getContacts(): Single<List<Contact>> {
        return contactsApi
            .getContacts()
            .map(contactMapper::transform)
    }
}