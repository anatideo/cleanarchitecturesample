package com.picpay.desafio.android.features.contacts.presentation.mappers

import com.picpay.desafio.android.core.domain.BaseMapper
import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.presentation.models.ViewContact

class ViewContactMapper : BaseMapper<Contact, ViewContact>() {
    override fun transform(entity: Contact): ViewContact {
        return ViewContact(
            img =  entity.img.orEmpty(),
            name = entity.name.orEmpty(),
            id = entity.id ?: 0,
            username = entity.username.orEmpty()
        )
    }
}