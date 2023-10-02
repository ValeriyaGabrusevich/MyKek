package com.example.myapplication.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ViewHolderCountriesBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private val items = mutableListOf<Country>()

    class CountryViewHolder(val binding: ViewHolderCountriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCountriesBinding.inflate(inflater, parent, false)

        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = items[position]
        val context = holder.itemView.context

        with(holder.binding) {
            textViewName.text = country.name
            twCountryId.text = country.id.toString()

            val url = BASE_URL + country.code + PREFIX
            Glide.with(context).load(url).into(imageViewFlag)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<Country>) {
        this.items.addAll(items)
    }

    companion object {
        private const val BASE_URL = "https://www.countryflagicons.com/FLAT/64/"
        private const val PREFIX = ".png"
    }
}