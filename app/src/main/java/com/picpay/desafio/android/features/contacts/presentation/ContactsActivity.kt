package com.picpay.desafio.android.features.contacts.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.core.presentation.extensions.observeOn
import com.picpay.desafio.android.features.contacts.presentation.models.ContactsViewState
import com.picpay.desafio.android.features.contacts.presentation.models.ViewContact
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity(R.layout.activity_contacts) {

    private val viewModel by viewModel<ContactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onGetContacts()
    }

    private fun setViewModel() {
        viewModel.viewState.observeOn(this) { viewState ->
            when (viewState) {
                is ContactsViewState.ShowList -> setList(viewState.list)
                is ContactsViewState.ListIsEmpty -> setEmptyList()
                is ContactsViewState.ShowLoading -> setLoading(viewState.show)
                is ContactsViewState.ShowError -> setError()
            }
        }
    }
    
    private fun setLoading(show: Boolean) {
        user_list_progress_bar.visibility = if (show) View.VISIBLE else View.VISIBLE
    }

    private fun setList(list: List<ViewContact>) {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = ContactListAdapter(list)
        }
    }

    private fun setError() {
        Toast.makeText(this@ContactsActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    private fun setEmptyList() {
        Toast.makeText(this@ContactsActivity, getString(R.string.empty_list), Toast.LENGTH_SHORT)
            .show()
    }
}
