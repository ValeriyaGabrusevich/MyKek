package com.example.myapplication.country

class CountryRepository {

    val countries = mutableListOf<Country>().apply {
        add(Country(1, "Russia", "RU"))
        add(Country(2, "Germany", "DE"))
        add(Country(3, "France", "FR"))
        add(Country(4, "United Arabian \nEmirates", "AE"))
        add(Country(5, "Armenia", "AM"))
        add(Country(6, "Austria", "AT"))
        add(Country(7, "Australia", "AU"))
        add(Country(8, "Argentina", "AR"))
        add(Country(9, "Azerbaijan", "AZ"))
        add(Country(10, "Albania", "AL"))
        add(Country(11, "Bulgaria", "BG"))
        add(Country(12, "Barbados", "BB"))
        add(Country(13, "Belgium", "BE"))
        add(Country(14, "Bolivia", "BO"))
        add(Country(15, "Canada", "CA"))
        add(Country(16, "Congo", "CG"))
        add(Country(17, "Switzerland", "CH"))
        add(Country(18, "Colombia", "CO"))
        add(Country(19, "Dominica", "DM"))
        add(Country(20, "Algeria", "DZ"))
        add(Country(21, "Spain", "ES"))
        add(Country(22, "United Kingdom \nof Great Britain \nand North Ireland", "GB"))
        add(Country(23, "Georgia", "GE"))
        add(Country(24, "Greece", "GR"))
        add(Country(25, "Croatia", "HR"))
        add(Country(26, "Hungary", "HU"))
        add(Country(27, "Indonesia", "ID"))
        add(Country(28, "India", "IN"))
        add(Country(29, "Italy", "IT"))
        add(Country(30, "Japan", "JP"))
    }

}