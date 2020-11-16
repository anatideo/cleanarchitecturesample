package com.picpay.desafio.android.features.contacts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.features.contacts.data.ContactsRepository
import com.picpay.desafio.android.core.presentation.BaseViewModel
import com.picpay.desafio.android.features.contacts.presentation.mappers.ViewContactMapper
import com.picpay.desafio.android.features.contacts.presentation.models.ContactsViewState

class ContactsViewModel(
    private val contactsRepository: ContactsRepository,
    private val viewContactMapper: ViewContactMapper
) : BaseViewModel() {
    private val _viewState = MutableLiveData<ContactsViewState>()
    val viewState: LiveData<ContactsViewState> get() = _viewState

    fun onGetContacts() {
        contactsRepository.getContacts()
            .map(viewContactMapper::transform)
            .subscribeOnUi(
                onSubscribe = {
                    _viewState.value = ContactsViewState.ShowLoading(true)
                },
                afterTerminate = {
                    _viewState.value = ContactsViewState.ShowLoading(false)
                },
                onSuccess = { list ->
                    _viewState.value = ContactsViewState.ShowList(list)
                },
                onError = {
                    _viewState.value = ContactsViewState.ShowError
                    false
                }
            )
    }
}