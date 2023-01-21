package ru.perelyginva.myproject.data.repository


import androidx.lifecycle.LiveData
import ru.perelyginva.myproject.data.localDB.OrderLocalDao
import ru.perelyginva.myproject.data.models.OrderLocalModel
import ru.perelyginva.myproject.domain.repository.OrderLocalCall


class OrderLocalRepository(private val orderLocalDao: OrderLocalDao) : OrderLocalCall {

    override suspend fun insert(orderLocalModel: OrderLocalModel) {
        orderLocalDao.insert(orderLocalModel)
    }

    override fun loadOrder(): LiveData<List<OrderLocalModel>>{
       return orderLocalDao.loadOrder()
    }


    override suspend fun deleteAllOrders() {
        orderLocalDao.deleteAll()
    }
}