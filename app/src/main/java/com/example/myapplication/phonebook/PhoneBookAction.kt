package com.example.myapplication.phonebook

sealed class PhoneBookAction {
    class EditContact(val contact: Contact, val position: Int) : PhoneBookAction()
    class RemoveContact(val position: Int) : PhoneBookAction()
    object AddContact : PhoneBookAction()
}
