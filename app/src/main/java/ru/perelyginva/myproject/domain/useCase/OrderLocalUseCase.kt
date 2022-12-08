package ru.perelyginva.myproject.domain.useCase


import ru.perelyginva.myproject.data.models.OrderLocalModel
import ru.perelyginva.myproject.domain.repository.OrderLocalCall

class OrderLocalUseCase(private val orderLocalCall: OrderLocalCall) {

    suspend fun insert(orderLocalModel: OrderLocalModel) {

        orderLocalCall.insert(orderLocalModel)
    }


}