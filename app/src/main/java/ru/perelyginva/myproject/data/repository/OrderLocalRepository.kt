package ru.perelyginva.myproject.data.repository


import ru.perelyginva.myproject.data.localDB.OrderLocalDao
import ru.perelyginva.myproject.data.models.OrderLocalModel
import ru.perelyginva.myproject.domain.repository.OrderLocalCall


class OrderLocalRepository(private val orderLocalDao: OrderLocalDao) : OrderLocalCall {

    override suspend fun insert(orderLocalModel: OrderLocalModel) {
        orderLocalDao.insert(orderLocalModel)
    }
}