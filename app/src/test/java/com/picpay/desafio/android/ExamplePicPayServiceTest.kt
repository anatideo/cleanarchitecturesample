package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.features.contacts.data.network.ContactsApi
import com.picpay.desafio.android.features.contacts.data.models.DataContact
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExamplePicPayServiceTest {

    private val api = mock<ContactsApi>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
//        // given
//        val call = mock<Call<List<DataContact>>>()
//        val expectedUsers = emptyList<DataContact>()
//
//        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
//        whenever(api.getContacts()).thenReturn(call)
//
//        // when
//        val users = service.example()
//
//        // then
//        assertEquals(users, expectedUsers)
    }
}