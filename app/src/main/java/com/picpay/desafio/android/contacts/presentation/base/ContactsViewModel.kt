package com.picpay.desafio.android.contacts.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.contacts.data.ContactsRepository
import com.picpay.desafio.android.contacts.presentation.ContactsViewState

class ContactsViewModel(
    private val contactsRepository: ContactsRepository
) : BaseViewModel() {
    private val _viewState = MutableLiveData<ContactsViewState>()
    val viewState: LiveData<ContactsViewState> get() = _viewState

    fun onGetContacts() {
        contactsRepository.getContacts()
//            .map(mapper::transform)
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