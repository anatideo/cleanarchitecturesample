package com.picpay.desafio.android.features.contacts.domain.usecases

import com.picpay.desafio.android.core.data.localsources.CoreLocalSource
import com.picpay.desafio.android.features.contacts.domain.models.CacheStatus
import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.domain.repositories.ContactsCacheRepository
import com.picpay.desafio.android.features.contacts.domain.repositories.ContactsRepository
import io.reactivex.Single

class ContactsUseCaseImpl(
    private val coreLocalSource: CoreLocalSource,
    private val contactsRepository: ContactsRepository,
    private val contactsCacheRepository: ContactsCacheRepository
): ContactsUseCase {
    override fun getContacts(): Single<List<Contact>> {
        return when (coreLocalSource.getCacheStatus()) {
            CacheStatus.OK -> contactsCacheRepository.getContacts()
            CacheStatus.NULL -> {
                contactsRepository
                    .getContacts()
                    .map { list ->
                        if (list.isNotEmpty()) {
                            contactsCacheRepository.saveContacts(list)
                            coreLocalSource.setCacheStatus(CacheStatus.OK)
                        }
                        list
                    }
            }
        }
    }
}