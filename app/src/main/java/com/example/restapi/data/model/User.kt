package com.example.restapi.data.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class User(
    val email: String,
    val password: String
)
