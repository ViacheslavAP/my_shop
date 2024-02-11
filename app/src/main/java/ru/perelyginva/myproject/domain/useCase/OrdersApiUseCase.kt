package ru.perelyginva.myproject.domain.useCase

import ru.perelyginva.myproject.domain.repository.OrdersApiCall

class OrdersApiUseCase(private var ordersApiCall: OrdersApiCall) {

     fun insert(name:String, phone:String, descriptions:String, priceOrder:String){
       ordersApiCall.insert(name, phone, descriptions, priceOrder)
   }


}