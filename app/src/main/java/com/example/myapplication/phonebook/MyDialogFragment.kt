package com.example.myapplication.phonebook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.DialogFragmentContactBinding

class MyDialogFragment : DialogFragment() {
    private var _binding: DialogFragmentContactBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainViewModel by activityViewModels()
    private val posititon: Int by lazy { requireArguments().getInt(POSITION_ARG, DEFAULT_POSITION) }
    private val contact: Contact? by lazy { requireArguments().get(PRODUCT_ARG) as? Contact }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        binding.etPhonebookSurname.setText(contact?.surname)
        binding.etPhonebookName.setText(contact?.name)
        binding.etPhonebookNumber.setText(contact?.number)
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            dismiss()
        }
        binding.btnDelete.setOnClickListener {
            contact?.let { sharedViewModel.onRemoveClick(it, position = posititon) }
            dismiss()
        }
        binding.btnChek.setOnClickListener {
            val name = binding.etPhonebookName.text.toString()
            val surname = binding.etPhonebookSurname.text.toString()
            val number = binding.etPhonebookNumber.text.toString()
            if (contact != null) {
                contact?.let {
                    sharedViewModel.onEditContact(
                        it.id,
                        name,
                        surname,
                        number,
                        posititon
                    )
                }
            } else {
                sharedViewModel.onAddContact(
                    name = name,
                    surname = surname,
                    number = number
                )
            }
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.oval))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "DIALOG_FRAGMENT_TAG"
        const val PRODUCT_ARG = "PRODUCT_ARG"
        const val POSITION_ARG = "POSITION_ARG"
        private const val DEFAULT_POSITION = -1

        @JvmStatic
        fun newInstance(contact: Contact?, position: Int?) = MyDialogFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PRODUCT_ARG, contact)
                position?.let { putInt(POSITION_ARG, it) }
            }
        }
    }
}