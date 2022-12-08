package ru.perelyginva.myproject.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.perelyginva.myproject.data.models.OrderLocalModel

@Database(entities = [OrderLocalModel::class], version = 1)
abstract class OrderDB: RoomDatabase() {

    abstract val orderLocalDao: OrderLocalDao
}