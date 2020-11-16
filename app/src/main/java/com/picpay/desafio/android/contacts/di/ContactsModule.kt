package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.data.ContactsRepository
import com.picpay.desafio.android.contacts.data.ContactsRepositoryImpl
import com.picpay.desafio.android.contacts.data.network.ContactsApi
import com.picpay.desafio.android.contacts.presentation.base.ContactsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ContactsModule {

    private const val CONTACTS_URL = "http://careers.picpay.com/tests/mobdev/"

    val instance = module {
        single<ContactsApi> { createWebService(url = CONTACTS_URL) }
        single<ContactsRepository> { ContactsRepositoryImpl(contactsApi = get()) }
        viewModel { ContactsViewModel(contactsRepository = get()) }
    }

    private inline fun <reified T> createWebService(url: String): T {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .client(OkHttpClient.Builder().build())
            .build()
            .create(T::class.java)
    }
}