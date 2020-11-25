package com.anatideo.cleanarchitecture.android.features.contacts.data.localsources

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.anatideo.cleanarchitecture.android.features.contacts.data.models.DataContact
import io.reactivex.Single

class ContactsLocalSourceImpl(
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) : ContactsLocalSource {
    override fun saveContacts(list: List<DataContact>) {
        val value = gson.toJson(list)

        sharedPreferences.edit().run {
            putString(CONTACTS_KEY, value)
            commit()
        }
    }

    override fun getContacts(): Single<List<DataContact>> {
        val value = sharedPreferences.getString(CONTACTS_KEY, null)
        val typeToken = object : TypeToken<List<DataContact>>() {}.type

        val result = value?.let { gson.fromJson<List<DataContact>>(it, typeToken) } ?: emptyList()
        return Single.just(result)
    }

    companion object {
        private const val CONTACTS_KEY = "CONTACTS_KEY"
    }
}