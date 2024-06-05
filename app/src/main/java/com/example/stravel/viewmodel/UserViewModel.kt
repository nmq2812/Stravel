package com.example.stravel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.stravel.model.Account
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserViewModel(): ViewModel() {
    val userAccounts: MutableList<Account> = mutableListOf(Account())
    private val accountReference: DatabaseReference = Firebase.database.getReference("Accounts")

    init {
        val database = accountReference
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (accountSnapshot in snapshot.children) {
                    val account = accountSnapshot.getValue<Account>()
                    if (account != null && !userAccounts.contains(account)) {
                        userAccounts.add(account)
                    }
                }
                println(userAccounts.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        database.addValueEventListener(dataListener)
    }

    fun addAccount(acc: Account) {
        userAccounts.add(acc)
    }

    fun getAccountInfo(email: String) : Account {
        val result = userAccounts.find { it.email == email }
        if (result != null) {
            return result
        } else {
            println("cannot get account")
            return Account()
        }
    }


}