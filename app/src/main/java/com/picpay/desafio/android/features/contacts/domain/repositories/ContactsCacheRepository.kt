package com.picpay.desafio.android.features.contacts.domain.repositories

import com.picpay.desafio.android.features.contacts.domain.models.Contact
import io.reactivex.Single

interface ContactsCacheRepository {
    fun getContacts(): Single<List<Contact>>
    fun saveContacts(list: List<Contact>)
}