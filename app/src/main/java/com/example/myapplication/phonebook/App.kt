package com.example.myapplication.phonebook

import android.app.Application

class App: Application() {
    val contactsService = ContactsService()
    val contactRepository = ContactRepository(contactsService)
}
