package com.anatideo.cleanarchitecture.android.features.contacts.domain.usecases

import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.Contact
import io.reactivex.Single

interface ContactsUseCase {
    fun getContacts(): Single<List<Contact>>
}