package com.picpay.desafio.android.features.contacts.presentation.mappers

import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.presentation.models.ViewContact
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ViewContactMapperTest {

    private val mapper = ViewContactMapper()

    @Test
    fun `GIVEN fake input WHEN transforming it THEN success`() {
        // Given / WHEN
        val result = mapper.transform(FAKE_INPUT)

        assertEquals(FAKE_OUTPUT.img, result.img)
        assertEquals(FAKE_OUTPUT.name, result.name)
        assertEquals(FAKE_OUTPUT.id, result.id)
        assertEquals(FAKE_OUTPUT.username, result.username)
    }

    companion object {
        private val FAKE_INPUT = Contact(
            img = "img",
            name = "name",
            id = 1,
            username = "username"
        )

        private val FAKE_OUTPUT = ViewContact(
            img = "img",
            name = "name",
            id = 1,
            username = "username"
        )
    }
}