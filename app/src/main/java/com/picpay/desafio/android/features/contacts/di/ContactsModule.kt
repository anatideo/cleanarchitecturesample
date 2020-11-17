package com.picpay.desafio.android.features.contacts.di

import com.picpay.desafio.android.features.contacts.data.localsources.ContactsLocalSource
import com.picpay.desafio.android.features.contacts.data.localsources.ContactsLocalSourceImpl
import com.picpay.desafio.android.features.contacts.data.mappers.ContactMapper
import com.picpay.desafio.android.features.contacts.data.mappers.DataContactMapper
import com.picpay.desafio.android.features.contacts.data.network.ContactsApi
import com.picpay.desafio.android.features.contacts.data.repositories.ContactsRepositoryImpl
import com.picpay.desafio.android.features.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.features.contacts.domain.usecases.ContactsUseCase
import com.picpay.desafio.android.features.contacts.domain.usecases.ContactsUseCaseImpl
import com.picpay.desafio.android.features.contacts.presentation.ContactsViewModel
import com.picpay.desafio.android.features.contacts.presentation.mappers.ViewContactMapper
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ContactsModule {

    private const val CONTACTS_URL = "http://careers.picpay.com/tests/mobdev/"

    val instance = module {
        // data
        single<ContactsApi> { createWebService(url = CONTACTS_URL) }
        single<ContactsRepository> {
            ContactsRepositoryImpl(
                contactsApi = get(),
                contactMapper = get()
            )
        }
        single<ContactsLocalSource> {
            ContactsLocalSourceImpl(
                sharedPreferences = get(),
                gson = get()
            )
        }
        single { DataContactMapper() }

        // domain
        single<ContactsUseCase> {
            ContactsUseCaseImpl(
                contactsRepository = get(),
                cacheRepository = get()
            )
        }
        single { ContactMapper() }

        // presentation
        viewModel { ContactsViewModel(contactsUseCase = get(), viewContactMapper = get()) }
        single { ViewContactMapper() }
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