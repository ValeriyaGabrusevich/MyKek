package com.example.myapplication.country

import java.io.Serializable

data class Country(
    val id: Int,
    val name: String,
    val code: String
) : Serializable