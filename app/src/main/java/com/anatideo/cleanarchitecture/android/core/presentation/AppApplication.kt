package com.anatideo.cleanarchitecture.android.core.presentation

import android.app.Application
import com.anatideo.cleanarchitecture.android.core.di.CoreModule
import com.anatideo.cleanarchitecture.android.features.contacts.di.ContactsModule
import com.anatideo.cleanarchitecture.android.features.contacts.domain.repositories.CacheRepository
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    private val cacheRepository: CacheRepository by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(MODULES)
        }

        cacheRepository.invalidateCache()
    }

    companion object {
        private val MODULES = listOf(
            CoreModule.instance,
            ContactsModule.instance
        )
    }
}