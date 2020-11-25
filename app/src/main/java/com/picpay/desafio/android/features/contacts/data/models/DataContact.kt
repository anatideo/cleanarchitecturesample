package com.picpay.desafio.android.features.contacts.data.models

import com.google.gson.annotations.SerializedName

class DataContact(
    @SerializedName("img") val img: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("username") val username: String?
)