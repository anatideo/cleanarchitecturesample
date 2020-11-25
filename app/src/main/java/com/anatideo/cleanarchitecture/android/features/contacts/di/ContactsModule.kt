package com.anatideo.cleanarchitecture.android.features.contacts.di

import com.anatideo.cleanarchitecture.android.core.di.CoreModule.createWebService
import com.anatideo.cleanarchitecture.android.features.contacts.data.localsources.ContactsLocalSource
import com.anatideo.cleanarchitecture.android.features.contacts.data.localsources.ContactsLocalSourceImpl
import com.anatideo.cleanarchitecture.android.features.contacts.data.mappers.ContactMapper
import com.anatideo.cleanarchitecture.android.features.contacts.data.mappers.DataContactMapper
import com.anatideo.cleanarchitecture.android.features.contacts.data.network.ContactsApi
import com.anatideo.cleanarchitecture.android.features.contacts.data.repositories.ContactsRepositoryImpl
import com.anatideo.cleanarchitecture.android.features.contacts.domain.repositories.ContactsRepository
import com.anatideo.cleanarchitecture.android.features.contacts.domain.usecases.ContactsUseCase
import com.anatideo.cleanarchitecture.android.features.contacts.domain.usecases.ContactsUseCaseImpl
import com.anatideo.cleanarchitecture.android.features.contacts.presentation.ContactsViewModel
import com.anatideo.cleanarchitecture.android.features.contacts.presentation.mappers.ViewContactMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

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
}