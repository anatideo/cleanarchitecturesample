package com.picpay.desafio.android.core.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.picpay.desafio.android.core.data.localsources.CoreLocalSource
import com.picpay.desafio.android.core.data.localsources.CoreLocalSourceImpl
import com.picpay.desafio.android.core.data.repositories.CacheRepositoryImpl
import com.picpay.desafio.android.features.contacts.domain.repositories.CacheRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object CoreModule {
    private const val PREFS_NAME = "app_general_prefs_content"

    val instance = module {
        factory { Gson() }
        single<SharedPreferences> {
            androidContext().getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
        }
        single<CoreLocalSource> { CoreLocalSourceImpl(sharedPreferences = get()) }
        single<CacheRepository> {
            CacheRepositoryImpl(
                coreLocalSource = get(),
                localSource = get(),
                contactMapper = get(),
                dataContactMapper = get()
            )
        }
    }
}