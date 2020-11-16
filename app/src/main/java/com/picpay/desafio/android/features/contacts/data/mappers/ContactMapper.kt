package com.picpay.desafio.android.features.contacts.data.mappers

import com.picpay.desafio.android.core.domain.BaseMapper
import com.picpay.desafio.android.features.contacts.data.models.DataContact
import com.picpay.desafio.android.features.contacts.domain.models.Contact

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