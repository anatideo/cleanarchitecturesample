package com.anatideo.cleanarchitecture.android.features.contacts.data.mappers

import com.anatideo.cleanarchitecture.android.core.domain.BaseMapper
import com.anatideo.cleanarchitecture.android.features.contacts.data.models.DataContact
import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.Contact

class DataContactMapper : BaseMapper<Contact, DataContact>() {
    override fun transform(entity: Contact): DataContact {
        return DataContact(
            img =  entity.img,
            name = entity.name,
            id = entity.id,
            username = entity.username
        )
    }
}