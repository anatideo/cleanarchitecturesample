package com.picpay.desafio.android.features.contacts.data.mappers

import com.picpay.desafio.android.core.domain.BaseMapper
import com.picpay.desafio.android.features.contacts.data.models.DataContact
import com.picpay.desafio.android.features.contacts.domain.models.Contact

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