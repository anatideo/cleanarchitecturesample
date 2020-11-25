package com.anatideo.cleanarchitecture.android.core.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.anatideo.cleanarchitecture.android.core.data.localsources.CoreLocalSource
import com.anatideo.cleanarchitecture.android.core.data.localsources.CoreLocalSourceImpl
import com.anatideo.cleanarchitecture.android.core.data.repositories.CacheRepositoryImpl
import com.anatideo.cleanarchitecture.android.features.contacts.domain.repositories.CacheRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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

    inline fun <reified T> createWebService(url: String): T {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .client(OkHttpClient.Builder().build())
            .build()
            .create(T::class.java)
    }
}