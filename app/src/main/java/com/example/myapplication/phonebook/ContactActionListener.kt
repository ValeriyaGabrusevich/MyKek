package com.example.myapplication.phonebook

interface ContactActionListener {
    fun onRemoveClick(contact: Contact, position: Int)
    fun onEditClick (contact: Contact, position: Int)
    fun onUpdateAfterLongClick(position: Int)
}