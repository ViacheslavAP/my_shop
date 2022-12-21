package ru.perelyginva.myproject.domain.repository

interface OrdersApiCall {

    fun insert(name:String, phone:String, descriptions:String, priceOrder:String)

}