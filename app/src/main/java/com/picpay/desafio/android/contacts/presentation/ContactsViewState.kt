package com.picpay.desafio.android.contacts.presentation

import com.picpay.desafio.android.contacts.data.DataContact

sealed class ContactsViewState {
    data class ShowList(val list: List<DataContact>): ContactsViewState()
    data class ShowLoading(val show: Boolean): ContactsViewState()
    object ListIsEmpty: ContactsViewState()
    object ShowError: ContactsViewState()
}