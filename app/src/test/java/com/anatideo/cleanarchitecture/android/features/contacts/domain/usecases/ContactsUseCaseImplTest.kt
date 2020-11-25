package com.anatideo.cleanarchitecture.android.features.contacts.domain.usecases

import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.CacheStatus
import com.anatideo.cleanarchitecture.android.features.contacts.domain.models.Contact
import com.anatideo.cleanarchitecture.android.features.contacts.domain.repositories.CacheRepository
import com.anatideo.cleanarchitecture.android.features.contacts.domain.repositories.ContactsRepository
import com.anatideo.cleanarchitecture.android.helper.RxImmediateSchedulerRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactsUseCaseImplTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @MockK
    private lateinit var contactsRepository: ContactsRepository

    @MockK
    private lateinit var cacheRepository: CacheRepository

    private lateinit var useCase: ContactsUseCase

    @Before
    fun setup() {
        clearAllMocks()
        MockKAnnotations.init(this)
        useCase = ContactsUseCaseImpl(
            contactsRepository = contactsRepository,
            cacheRepository = cacheRepository
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `GIVEN fake list n' valid cache WHEN getting it THEN assert success`() {
        // Given
        every { cacheRepository.getContacts() } returns Single.just(FAKE_CONTACT_LIST)
        every { cacheRepository.getCacheStatus() } returns CacheStatus.VALID

        // When / Then
        useCase.getContacts()
            .test()
            .assertNoErrors()
            .assertValue(FAKE_CONTACT_LIST)

        verify(exactly = 0) {
            cacheRepository.saveContacts(any())
            cacheRepository.setCacheStatus(any())
        }
    }

    @Test
    fun `GIVEN fake list n' invalid cache WHEN getting it THEN assert success`() {
        // Given
        every { contactsRepository.getContacts() } returns Single.just(FAKE_CONTACT_LIST)
        every { cacheRepository.getCacheStatus() } returns CacheStatus.INVALID
        every { cacheRepository.saveContacts(any()) } just Runs
        every { cacheRepository.setCacheStatus(any()) } just Runs

        // When / Then
        useCase.getContacts()
            .test()
            .assertNoErrors()
            .assertValue(FAKE_CONTACT_LIST)

        verify(exactly = 1) {
            cacheRepository.saveContacts(FAKE_CONTACT_LIST)
            cacheRepository.setCacheStatus(CacheStatus.VALID)
        }
    }

    @Test
    fun `GIVEN fake empty list n' invalid cache WHEN getting it THEN assert success`() {
        // Given
        val list = emptyList<Contact>()
        every { contactsRepository.getContacts() } returns Single.just(list)
        every { cacheRepository.getCacheStatus() } returns CacheStatus.INVALID
        every { cacheRepository.saveContacts(any()) } just Runs
        every { cacheRepository.setCacheStatus(any()) } just Runs

        // When / Then
        useCase.getContacts()
            .test()
            .assertNoErrors()
            .assertValue(list)

        verify(exactly = 0) {
            cacheRepository.saveContacts(any())
            cacheRepository.setCacheStatus(any())
        }
    }

    companion object {
        private val FAKE_CONTACT_LIST = listOf(
            Contact(
                img = "img",
                name = "name",
                id = 100,
                username = "username"
            ),
            Contact(
                img = "img",
                name = "name",
                id = 100,
                username = "username"
            )
        )
    }
}