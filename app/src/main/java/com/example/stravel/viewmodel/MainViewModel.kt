package com.example.stravel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.stravel.model.PlaceItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainViewModel: ViewModel() {
    var listOfPlaceItems: MutableList<PlaceItem> = mutableListOf()
    private val placeItemReference: DatabaseReference = Firebase.database.getReference("PlaceItem")

    init {
        val database = placeItemReference
        val dataListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (pItemSnapshot in snapshot.children) {
                    val pItem = pItemSnapshot.getValue<PlaceItem>()
                    if (pItem != null && !listOfPlaceItems.contains(pItem)) {
                        listOfPlaceItems.add(pItem)
                    }
                }
                listOfPlaceItems.sortBy { it.id }
                println(listOfPlaceItems.toString())
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        database.addValueEventListener(dataListener)
    }
    override fun onCleared() {
        super.onCleared()
        // Dispose All your Subscriptions to avoid memory leaks
    }
}
