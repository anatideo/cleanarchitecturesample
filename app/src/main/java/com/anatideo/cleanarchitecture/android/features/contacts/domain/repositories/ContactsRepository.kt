package com.anatideo.cleanarchitecture.android.features.contacts.domain.repositories

import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.Contact
import io.reactivex.Single

interface ContactsRepository {
    fun getContacts(): Single<List<Contact>>
}