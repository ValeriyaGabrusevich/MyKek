package com.example.myapplication.phonebook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewHolderPhonebookBinding

class ContactAdapter(private val contactActionListener: ContactActionListener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val items = mutableListOf<Contact>()

    class ContactViewHolder(val binding: ViewHolderPhonebookBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderPhonebookBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = items[position]

        with(holder.binding) {
            val background = if (contact.isSelected) {
                R.drawable.selection
            } else {
                R.drawable.oval
            }
            root.background = AppCompatResources.getDrawable(root.context, background)
            twPhonebookId.text = contact.id.toString()
            etPhonebookName.text = contact.name
            etPhonebookSurname.text = contact.surname
            etPhonebookNumber.text = contact.number

            root.setOnLongClickListener {
                contactActionListener.onUpdateAfterLongClick(position)
                true
            }

            root.setOnClickListener {
                contactActionListener.onEditClick(contact, position)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setItems(newItems: List<Contact>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffUtilContactCallback(
                items,
                newItems
            )
        )
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

}