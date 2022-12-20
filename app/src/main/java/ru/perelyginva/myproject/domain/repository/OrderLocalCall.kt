package ru.perelyginva.myproject.domain.repository

import androidx.lifecycle.LiveData
import ru.perelyginva.myproject.data.models.OrderLocalModel

interface OrderLocalCall {

    suspend fun insert(orderLocalModel: OrderLocalModel)

    fun loadOrder(): LiveData<List<OrderLocalModel>>
}