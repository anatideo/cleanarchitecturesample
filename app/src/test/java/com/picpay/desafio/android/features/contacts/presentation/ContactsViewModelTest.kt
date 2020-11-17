package com.picpay.desafio.android.features.contacts.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picpay.desafio.android.features.contacts.domain.models.Contact
import com.picpay.desafio.android.features.contacts.domain.usecases.ContactsUseCase
import com.picpay.desafio.android.features.contacts.presentation.mappers.ViewContactMapper
import com.picpay.desafio.android.features.contacts.presentation.models.ContactsViewState
import com.picpay.desafio.android.features.contacts.presentation.models.ViewContact
import com.picpay.desafio.android.helper.RxImmediateSchedulerRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactsViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var useCase: ContactsUseCase

    @MockK
    private lateinit var mapper: ViewContactMapper

    private lateinit var viewModel: ContactsViewModel

    @Before
    fun setup() {
        clearAllMocks()
        MockKAnnotations.init(this)
        viewModel = ContactsViewModel(
            contactsUseCase = useCase,
            viewContactMapper = mapper
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `GIVEN fake list WHEN getting contacts THEN show it`() {
        val mockedObserver = getViewStateObserver()
        viewModel.viewState.observeForever(mockedObserver)

        // Given
        every { useCase.getContacts() } returns Single.just(FAKE_CONTACT_LIST)
        every { mapper.transform(any<List<Contact>>()) } returns FAKE_VIEW_CONTACT_LIST

        // When
        viewModel.onGetContacts()

        val slots = mutableListOf<ContactsViewState>()
        verify { mockedObserver.onChanged(capture(slots)) }
        val trueLoadingState = slots[0]
        val showListState = slots[1]
        val falseLoadingState = slots[2]

        // Then
        assert(trueLoadingState is ContactsViewState.ShowLoading && trueLoadingState.show)
        assert(showListState is ContactsViewState.ShowList && showListState.list == FAKE_VIEW_CONTACT_LIST)
        assert(falseLoadingState is ContactsViewState.ShowLoading && !falseLoadingState.show)

        verify(exactly = 1) {
            mapper.transform(any<List<Contact>>())
            useCase.getContacts()
        }    }

    @Test
    fun `GIVEN empty list WHEN getting contacts THEN show empty state`() {
        val mockedObserver = getViewStateObserver()
        viewModel.viewState.observeForever(mockedObserver)

        // Given
        every { useCase.getContacts() } returns Single.just(emptyList())
        every { mapper.transform(any<List<Contact>>()) } returns emptyList()

        // When
        viewModel.onGetContacts()

        val slots = mutableListOf<ContactsViewState>()
        verify { mockedObserver.onChanged(capture(slots)) }
        val trueLoadingState = slots[0]
        val showListState = slots[1]
        val falseLoadingState = slots[2]

        // Then
        assert(trueLoadingState is ContactsViewState.ShowLoading && trueLoadingState.show)
        assert(showListState is ContactsViewState.ListIsEmpty)
        assert(falseLoadingState is ContactsViewState.ShowLoading && !falseLoadingState.show)

        verify(exactly = 1) {
            mapper.transform(any<List<Contact>>())
            useCase.getContacts()
        }
    }

    @Test
    fun `GIVEN fake error WHEN getting contacts THEN show it`() {
        val mockedObserver = getViewStateObserver()
        viewModel.viewState.observeForever(mockedObserver)

        // Given
        every { useCase.getContacts() } returns Single.error(Throwable())

        // When
        viewModel.onGetContacts()

        val slots = mutableListOf<ContactsViewState>()
        verify { mockedObserver.onChanged(capture(slots)) }
        val trueLoadingState = slots[0]
        val errorState = slots[1]
        val falseLoadingState = slots[2]

        // Then
        assert(trueLoadingState is ContactsViewState.ShowLoading && trueLoadingState.show)
        assert(errorState is ContactsViewState.ShowError)
        assert(falseLoadingState is ContactsViewState.ShowLoading && !falseLoadingState.show)

        verify(exactly = 1) {
            useCase.getContacts()
        }
    }

    private fun getViewStateObserver(): Observer<ContactsViewState> = spyk(Observer {  })

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

        private val FAKE_VIEW_CONTACT_LIST = listOf(
            ViewContact(
                img = "img",
                name = "name",
                id = 100,
                username = "username"
            ),
            ViewContact(
                img = "img",
                name = "name",
                id = 100,
                username = "username"
            )
        )
    }
}