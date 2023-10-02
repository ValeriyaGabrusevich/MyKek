package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.myapplication.country.FragmentCountries
import com.example.myapplication.phonebook.FragmentContact
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCountries.setOnClickListener {
            makeVisibility(true)
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainerView.id, FragmentCountries())
                .commit()
        }
        binding.btnNumbers.setOnClickListener {
            makeVisibility(true)
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainerView.id, FragmentContact())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        makeVisibility(false)
    }

    private fun makeVisibility(isVisibleFragment: Boolean) {
        binding.fragmentContainerView.isVisible = isVisibleFragment
        binding.btnCountries.isVisible = !isVisibleFragment
        binding.btnNumbers.isVisible = !isVisibleFragment
    }
}