package com.picpay.desafio.android.core.data.localsources

import com.picpay.desafio.android.features.contacts.domain.models.CacheStatus

interface CoreLocalSource {
    fun setCacheStatus(cacheStatus: CacheStatus)
    fun getCacheStatus(): CacheStatus
}