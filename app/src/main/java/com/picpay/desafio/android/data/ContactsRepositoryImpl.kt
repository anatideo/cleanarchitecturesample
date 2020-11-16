package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.network.ContactsApi
import com.picpay.desafio.android.data.network.NetWorkHelper
import retrofit2.Call

class ContactsRepositoryImpl(
        private val contactsApi: ContactsApi = NetWorkHelper.createApi(url = "http://careers.picpay.com/tests/mobdev/")
) : ContactsRepository {
    override fun getContacts(): Call<List<DataContact>> {
        return contactsApi.getContacts()
    }
}