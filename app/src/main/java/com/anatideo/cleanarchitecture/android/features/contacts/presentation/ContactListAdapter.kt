package com.anatideo.cleanarchitecture.android.features.contacts.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anatideo.cleanarchitecture.android.R
import com.anatideo.cleanarchitecture.android.features.contacts.presentation.models.ViewContact

class ContactListAdapter(
    private val list: List<ViewContact>
) : RecyclerView.Adapter<UserListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}