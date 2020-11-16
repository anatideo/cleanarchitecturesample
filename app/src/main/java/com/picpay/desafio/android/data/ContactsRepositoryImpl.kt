package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.network.PicPayService
import com.picpay.desafio.android.data.network.PicPayServiceImpl
import retrofit2.Call

class ContactsRepositoryImpl(
        private val picPayService: PicPayService = PicPayServiceImpl()
) : ContactsRepository {
    override fun getContacts(): Call<List<DataContact>> {
        return picPayService.getContacts()
    }
}