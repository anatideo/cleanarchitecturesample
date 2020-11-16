package com.picpay.desafio.android.features.contacts.domain.usecases

import com.picpay.desafio.android.features.contacts.domain.models.Contact
import io.reactivex.Single

interface ContactsUseCase {
    fun getContacts(): Single<List<Contact>>
}