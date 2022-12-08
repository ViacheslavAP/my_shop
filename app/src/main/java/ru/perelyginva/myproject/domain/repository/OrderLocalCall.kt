package ru.perelyginva.myproject.domain.repository

import ru.perelyginva.myproject.data.models.OrderLocalModel

interface OrderLocalCall {

    suspend fun insert(orderLocalModel: OrderLocalModel)
}