package com.anatideo.cleanarchitecture.android.features.contacts.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.anatideo.cleanarchitecture.android.R
import com.anatideo.cleanarchitecture.android.features.contacts.presentation.models.ViewContact
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(dataContact: ViewContact) {
        itemView.name.text = dataContact.name
        itemView.username.text = dataContact.username
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(dataContact.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.picture, object : Callback {
                override fun onSuccess() {
                    itemView.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemView.progressBar.visibility = View.GONE
                }
            })
    }
}