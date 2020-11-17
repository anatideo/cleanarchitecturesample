package com.picpay.desafio.android.features.contacts.domain.usecases

import com.picpay.desafio.android.features.contacts.domain.models.CacheStatus
import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.domain.repositories.CacheRepository
import com.picpay.desafio.android.features.contacts.domain.repositories.ContactsRepository
import io.reactivex.Single

class ContactsUseCaseImpl(
    private val contactsRepository: ContactsRepository,
    private val cacheRepository: CacheRepository
): ContactsUseCase {
    override fun getContacts(): Single<List<Contact>> {
        return when (cacheRepository.getCacheStatus()) {
            CacheStatus.VALID -> cacheRepository.getContacts()
            CacheStatus.INVALID -> {
                contactsRepository
                    .getContacts()
                    .map { list ->
                        if (list.isNotEmpty()) {
                            cacheRepository.saveContacts(list)
                            cacheRepository.setCacheStatus(CacheStatus.VALID)
                        }
                        list
                    }
            }
        }
    }
}