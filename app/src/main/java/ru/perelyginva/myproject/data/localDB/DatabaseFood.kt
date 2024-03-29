package ru.perelyginva.myproject.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.data.models.FoodModel
import ru.perelyginva.myproject.data.models.OrderLocalModel
import ru.perelyginva.myproject.data.models.OrdersApiModel

@Database(entities = [
    CartModel::class,
    FoodModel::class,
    OrderLocalModel::class,
    OrdersApiModel::class], version = 1)
abstract  class DatabaseFood: RoomDatabase() {

    abstract val cartDao: CartDao
    abstract val foodDao: FoodDao
    abstract val orderLocalDao: OrderLocalDao
}