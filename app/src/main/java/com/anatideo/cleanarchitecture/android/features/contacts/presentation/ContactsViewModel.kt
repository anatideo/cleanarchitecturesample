package com.anatideo.cleanarchitecture.android.features.contacts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anatideo.cleanarchitecture.android.core.presentation.BaseViewModel
import com.anatideo.cleanarchitecture.android.features.contacts.domain.usecases.ContactsUseCase
import com.anatideo.cleanarchitecture.android.features.contacts.presentation.mappers.ViewContactMapper
import com.anatideo.cleanarchitecture.android.features.contacts.presentation.models.ContactsViewState

class ContactsViewModel(
    private val contactsUseCase: ContactsUseCase,
    private val viewContactMapper: ViewContactMapper
) : BaseViewModel() {
    private val _viewState = MutableLiveData<ContactsViewState>()
    val viewState: LiveData<ContactsViewState> get() = _viewState

    fun onGetContacts() {
        contactsUseCase.getContacts()
            .map(viewContactMapper::transform)
            .subscribeOnUi(
                onSubscribe = {
                    _viewState.value = ContactsViewState.ShowLoading(true)
                },
                afterTerminate = {
                    _viewState.value = ContactsViewState.ShowLoading(false)
                },
                onSuccess = { list ->
                    _viewState.value = when {
                        list.isNullOrEmpty() -> ContactsViewState.ListIsEmpty
                        else -> ContactsViewState.ShowList(list)
                    }
                },
                onError = {
                    _viewState.value = ContactsViewState.ShowError
                }
            )
    }
}