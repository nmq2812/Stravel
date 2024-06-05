package com.example.stravel.model

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class Account(
    var id: String = "",
    val email: String = "",
    var userName: String = "",
    var fullName: String = "",
    var avatar: String = "",
    val walletId: Long = 0,
    val password: String = ""
) {
    fun pushAccount() {
        val accountReference: DatabaseReference = Firebase.database.getReference("Accounts")

        accountReference.child(this.userName).setValue(this)
    }
}