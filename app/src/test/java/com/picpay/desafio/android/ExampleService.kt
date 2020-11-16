package com.picpay.desafio.android

import com.picpay.desafio.android.contacts.data.network.ContactsApi
import com.picpay.desafio.android.contacts.data.DataContact

class ExampleService(
    private val serviceContacts: ContactsApi
) {

    fun example(): List<DataContact> {
        val users = serviceContacts.getContacts().execute()

        return users.body() ?: emptyList()
    }
}