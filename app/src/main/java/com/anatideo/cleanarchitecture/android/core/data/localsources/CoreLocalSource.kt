package com.anatideo.cleanarchitecture.android.core.data.localsources

import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.CacheStatus

interface CoreLocalSource {
    fun setCacheStatus(cacheStatus: CacheStatus)
    fun getCacheStatus(): CacheStatus
    fun invalidateCache()
}