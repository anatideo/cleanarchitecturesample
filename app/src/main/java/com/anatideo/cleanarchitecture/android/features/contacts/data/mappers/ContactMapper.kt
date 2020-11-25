package com.anatideo.cleanarchitecture.android.features.contacts.data.mappers

import com.anatideo.cleanarchitecture.android.core.domain.BaseMapper
import com.anatideo.cleanarchitecture.android.features.contacts.data.models.DataContact
import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.Contact

class ContactMapper : BaseMapper<DataContact, Contact>() {
    override fun transform(entity: DataContact): Contact {
        return Contact(
            img =  entity.img,
            name = entity.name,
            id = entity.id,
            username = entity.username
        )
    }
}