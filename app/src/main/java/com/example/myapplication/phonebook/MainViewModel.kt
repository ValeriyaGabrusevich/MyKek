package com.example.myapplication.phonebook

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras

class MainViewModel(repository: ContactRepository) : ViewModel(),
    ContactActionListener {
    private val _peopleLiveData = MutableLiveData<List<Contact>>()
    val peopleLiveData: LiveData<List<Contact>> = _peopleLiveData

    private val _buttonStateLiveData = MutableLiveData(false)
    val buttonStateLiveData: LiveData<Boolean> = _buttonStateLiveData


    private val _sideEffectLiveData = SingleLiveData<PhoneBookAction>()
    val sideEffectLiveData: LiveData<PhoneBookAction> = _sideEffectLiveData

    init {
        _peopleLiveData.value = repository.getContacts()
    }

    override fun onRemoveClick(contact: Contact, position: Int) {
        val newPeople = mutableListOf<Contact>()
        peopleLiveData.value?.let { newPeople.addAll(it) }
        contact.id.let { newPeople.removeAt(position) }
        _peopleLiveData.value = newPeople
    }

    fun onAddContact(name: String, surname: String, number: String) {
        val newPeople = mutableListOf<Contact>()
        val id = peopleLiveData.value?.last()?.id?.plus(1)
        peopleLiveData.value?.let { newPeople.addAll(it) }
        id?.let { newPeople.add(Contact(it, name, surname, number)) }
        _peopleLiveData.value = newPeople
    }

    fun onEditContact(id:Int, name: String, surname: String, number: String, position: Int) {
        val newContact = Contact(id, name, surname, number)
        val newPeople = mutableListOf<Contact>()
        peopleLiveData.value?.let { newPeople.addAll(it) }
        newPeople.removeAt(position)
        newPeople.add(position, newContact)
        _peopleLiveData.value = newPeople
    }

    fun onAddOrRemoveClick() {
        if (peopleLiveData.value?.let { isExistSelectedItems(it) } == true) {
            removeItems()
        } else {
            _sideEffectLiveData.value = PhoneBookAction.AddContact
        }
    }

    override fun onEditClick(contact: Contact, position: Int) {
        if (peopleLiveData.value?.any { it.isSelected } == true) {
            onUpdateAfterLongClick(position)
        } else {
            _sideEffectLiveData.value = PhoneBookAction.EditContact(contact, position)
        }
    }

    override fun onUpdateAfterLongClick(position: Int) {
        val newPeople = mutableListOf<Contact>()
        peopleLiveData.value?.let { newPeople.addAll(it) }
        val contact = newPeople[position]
        val newContact = Contact(contact.id, contact.name, contact.surname, contact.number, !contact.isSelected)
        newPeople.removeAt(position)
        newPeople.add(position, newContact)
        _peopleLiveData.value = newPeople
        _buttonStateLiveData.value = isExistSelectedItems(newPeople)
    }

    private fun isExistSelectedItems(items: List<Contact>): Boolean {
        var isSelected = false
        items.forEach {
            if (it.isSelected) {
                isSelected = true
                return@forEach
            }
        }
        return isSelected
    }

    private fun removeItems() {
        val newPeople = mutableListOf<Contact>()
        peopleLiveData.value?.let { newPeople.addAll(it) }
        newPeople.removeIf { it.isSelected }
        _peopleLiveData.value = newPeople
        _buttonStateLiveData.value = false
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return MainViewModel(
                    (application as App).contactRepository
                ) as T
            }
        }
    }
}