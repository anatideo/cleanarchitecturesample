package com.picpay.desafio.android.features.contacts.presentation.mappers

import com.picpay.desafio.android.core.domain.BaseMapper
import com.picpay.desafio.android.features.contacts.data.models.DataContact
import com.picpay.desafio.android.features.contacts.presentation.models.ViewContact

class ViewContactMapper : BaseMapper<DataContact, ViewContact>() {
    override fun transform(entity: DataContact): ViewContact {
        return ViewContact(
            img =  entity.img.orEmpty(),
            name = entity.name.orEmpty(),
            id = entity.id ?: 0,
            username = entity.username.orEmpty()
        )
    }
}