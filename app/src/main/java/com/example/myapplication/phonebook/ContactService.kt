package com.example.myapplication.phonebook

import com.github.javafaker.Faker

typealias ContactsListener = (contacts: List<Contact>) -> Unit
class ContactsService {
private val contacts = mutableListOf<Contact>()

    init {
        val faker = Faker.instance()

        for (i in 1 until 101) {
            contacts.add(
                Contact(
                    id = i,
                    name = faker.name().firstName(),
                    surname = faker.name().lastName(),
                    number = faker.phoneNumber().phoneNumber()
                )
            )
        }
    }
    fun getContacts(): List<Contact> {
        return contacts
    }
}
