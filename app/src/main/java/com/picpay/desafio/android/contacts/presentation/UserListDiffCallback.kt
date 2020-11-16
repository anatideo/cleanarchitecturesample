package com.picpay.desafio.android.contacts.presentation

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.contacts.data.DataContact

class UserListDiffCallback(
    private val oldList: List<DataContact>,
    private val newList: List<DataContact>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].username.equals(newList[newItemPosition].username)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}