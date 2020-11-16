package com.picpay.desafio.android.features.contacts.data.repositories

import com.picpay.desafio.android.features.contacts.data.localsources.ContactsLocalSource
import com.picpay.desafio.android.features.contacts.data.mappers.ContactMapper
import com.picpay.desafio.android.features.contacts.data.mappers.DataContactMapper
import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.domain.repositories.ContactsCacheRepository
import io.reactivex.Single

class ContactsCacheRepositoryImpl(
    private val localSource: ContactsLocalSource,
    private val contactMapper: ContactMapper,
    private val dataContactMapper: DataContactMapper
) : ContactsCacheRepository {
    override fun getContacts(): Single<List<Contact>> {
        return localSource
            .getContacts()
            .map(contactMapper::transform)
    }

    override fun saveContacts(list: List<Contact>) {
        localSource.saveContacts(dataContactMapper.transform(list))
    }
}