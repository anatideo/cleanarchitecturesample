package com.picpay.desafio.android.core.data.repositories

import com.picpay.desafio.android.core.data.localsources.CoreLocalSource
import com.picpay.desafio.android.features.contacts.data.localsources.ContactsLocalSource
import com.picpay.desafio.android.features.contacts.data.mappers.ContactMapper
import com.picpay.desafio.android.features.contacts.data.mappers.DataContactMapper
import com.picpay.desafio.android.features.contacts.domain.models.CacheStatus
import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.domain.repositories.CacheRepository
import io.reactivex.Single

class CacheRepositoryImpl(
    private val coreLocalSource: CoreLocalSource,
    private val localSource: ContactsLocalSource,
    private val contactMapper: ContactMapper,
    private val dataContactMapper: DataContactMapper
) : CacheRepository {
    override fun getContacts(): Single<List<Contact>> {
        return localSource
            .getContacts()
            .map(contactMapper::transform)
    }

    override fun saveContacts(list: List<Contact>) {
        localSource.saveContacts(dataContactMapper.transform(list))
    }

    override fun setCacheStatus(cacheStatus: CacheStatus) {
        coreLocalSource.setCacheStatus(cacheStatus)
    }

    override fun getCacheStatus(): CacheStatus {
        return coreLocalSource.getCacheStatus()
    }

    override fun invalidateCache() {
        coreLocalSource.invalidateCache()
    }
}