package com.example.myapplication.phonebook

class ContactRepository (private val service: ContactsService){
    fun getContacts(): List<Contact> = service.getContacts()
}
