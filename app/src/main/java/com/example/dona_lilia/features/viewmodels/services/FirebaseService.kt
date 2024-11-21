package com.example.dona_lilia.features.services

import com.example.dona_lilia.features.models.Products
import com.example.dona_lilia.features.models.Tickets
import com.example.dona_lilia.features.models.Users
import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseService {

    // Referencias a las tablas en la base de datos
    private val dbRT = FirebaseDatabase.getInstance().reference
    private val productsRef = dbRT.child("Products")
    private val ticketsRef = dbRT.child("Tickets")
    private val usersRef = dbRT.child("Users")

    // Productos
    fun addProduct(product: Products) {
        val key = productsRef.push().key
        if (key != null) {
            productsRef.child(key).setValue(product)
        }
    }

    fun deleteProduct(key: String) {
        productsRef.child(key).removeValue()
    }

    fun updateProduct(key: String, product: Products) {
        productsRef.child(key).setValue(product)
    }

    fun fetchProducts(): Flow<List<Products>> = callbackFlow {
        val listener = productsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = snapshot.children.mapNotNull { it.getValue(Products::class.java) }
                trySend(productList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        })
        awaitClose { productsRef.removeEventListener(listener) }
    }

    // Tickets
    fun addTicket(ticket: Tickets) {
        val key = ticketsRef.push().key
        if (key != null) {
            ticketsRef.child(key).setValue(ticket)
        }
    }

    fun deleteTicket(key: String) {
        ticketsRef.child(key).removeValue()
    }

    fun updateTicket(key: String, ticket: Tickets) {
        ticketsRef.child(key).setValue(ticket)
    }

    fun fetchTickets(): Flow<List<Tickets>> = callbackFlow {
        val listener = ticketsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val ticketList = snapshot.children.mapNotNull { it.getValue(Tickets::class.java) }
                trySend(ticketList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        })
        awaitClose { ticketsRef.removeEventListener(listener) }
    }

    // Usuarios
    fun addUser(user: Users) {
        val key = usersRef.push().key
        if (key != null) {
            usersRef.child(key).setValue(user)
        }
    }

    fun deleteUser(key: String) {
        usersRef.child(key).removeValue()
    }

    fun updateUser(key: String, user: Users) {
        usersRef.child(key).setValue(user)
    }

    fun fetchUsers(): Flow<List<Users>> = callbackFlow {
        val listener = usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = snapshot.children.mapNotNull { it.getValue(Users::class.java) }
                trySend(userList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        })
        awaitClose { usersRef.removeEventListener(listener) }
    }



}
