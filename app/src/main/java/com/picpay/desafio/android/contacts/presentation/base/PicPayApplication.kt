package com.picpay.desafio.android.contacts.presentation.base

import android.app.Application
import com.picpay.desafio.android.contacts.di.ContactsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PicPayApplication)
            modules(ContactsModule.instance)
        }
    }
}