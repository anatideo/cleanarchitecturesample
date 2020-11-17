package com.picpay.desafio.android.core.presentation

import android.app.Application
import com.picpay.desafio.android.core.di.CoreModule
import com.picpay.desafio.android.features.contacts.di.ContactsModule
import com.picpay.desafio.android.features.contacts.domain.repositories.CacheRepository
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    private val cacheRepository: CacheRepository by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicPayApplication)
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