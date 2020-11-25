package com.anatideo.cleanarchitecture.android.features.contacts.presentation.models

sealed class ContactsViewState {
    data class ShowList(val list: List<ViewContact>): ContactsViewState()
    data class ShowLoading(val show: Boolean): ContactsViewState()
    object ListIsEmpty: ContactsViewState()
    object ShowError: ContactsViewState()
}