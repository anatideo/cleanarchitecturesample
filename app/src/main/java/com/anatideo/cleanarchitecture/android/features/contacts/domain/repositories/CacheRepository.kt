package com.anatideo.cleanarchitecture.android.features.contacts.domain.repositories

import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.CacheStatus
import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.Contact
import io.reactivex.Single

interface CacheRepository {
    fun getContacts(): Single<List<Contact>>
    fun saveContacts(list: List<Contact>)
    fun setCacheStatus(cacheStatus: CacheStatus)
    fun getCacheStatus(): CacheStatus
    fun invalidateCache()
}