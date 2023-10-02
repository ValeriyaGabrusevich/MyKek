package com.example.myapplication.phonebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPhonebookBinding

class FragmentContact : Fragment() {
    private var _binding: FragmentPhonebookBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ContactAdapter

    private val viewModel by activityViewModels<MainViewModel> { MainViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhonebookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
        binding.btnAdd.setOnClickListener {
            viewModel.onAddOrRemoveClick()
        }
    }

    private fun observe() {
        val nameObserver = Observer<List<Contact>> { items ->
            adapter.setItems(items)
        }

        val buttonObserver = Observer<Boolean> { isSelected ->
            val drawable = if (isSelected) {
                R.drawable.baseline_delete_outline_24
            } else {
                R.drawable.baseline_person_add_24
            }

            binding.btnAdd.setImageResource(drawable)
        }

        val sideEffectObserver = Observer<PhoneBookAction> { action ->
            val contact: Contact?
            val position: Int?
            when (action) {
                is PhoneBookAction.AddContact -> {
                    contact = null
                    position = null
                }
                is PhoneBookAction.RemoveContact -> {
                    contact = null
                    position = action.position
                }
                is PhoneBookAction.EditContact -> {
                    contact = action.contact
                    position = action.position
                }
            }
            MyDialogFragment.newInstance(contact, position)
                .show(parentFragmentManager, MyDialogFragment.TAG)
        }

        viewModel.peopleLiveData.observe(this, nameObserver)
        viewModel.sideEffectLiveData.observe(this, sideEffectObserver)
        viewModel.buttonStateLiveData.observe(this, buttonObserver)
    }

    private fun initViews() {
        val manager = LinearLayoutManager(requireContext())
        adapter = ContactAdapter(viewModel)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
    }
}
