package com.anatideo.cleanarchitecture.android.core.data.localsources

import android.content.SharedPreferences
import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.CacheStatus
import com.anatideo.cleanarchitecture.android.core.domain.EnumHelper

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
        return EnumHelper.enumValueOfOrDefault(value, CacheStatus.INVALID)
    }

    override fun invalidateCache() {
        sharedPreferences.edit().run {
            clear()
            commit()
        }
    }

    companion object {
        private const val CACHE_STATUS_KEY = "CACHE_STATUS_KEY"
    }
}