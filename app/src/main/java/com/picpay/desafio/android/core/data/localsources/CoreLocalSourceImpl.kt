package com.picpay.desafio.android.core.data.localsources

import android.content.SharedPreferences
import com.picpay.desafio.android.features.contacts.domain.models.CacheStatus
import com.picpay.desafio.android.core.domain.EnumHelper

class CoreLocalSourceImpl(
    private val sharedPreferences: SharedPreferences
) : CoreLocalSource {
    override fun setCacheStatus(cacheStatus: CacheStatus) {
        sharedPreferences.edit().run {
            putString(CACHE_STATUS_KEY, cacheStatus.name)
            commit()
        }
    }

    override fun getCacheStatus(): CacheStatus {
        val value = sharedPreferences.getString(CACHE_STATUS_KEY, null)
        return EnumHelper.enumValueOfOrDefault(value, CacheStatus.NULL)
    }

    companion object {
        private const val CACHE_STATUS_KEY = "CACHE_STATUS_KEY"
    }
}