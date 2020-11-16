package com.picpay.desafio.android

import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.data.DataContact

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<DataContact> {
        val users = service.getContacts().execute()

        return users.body() ?: emptyList()
    }
}