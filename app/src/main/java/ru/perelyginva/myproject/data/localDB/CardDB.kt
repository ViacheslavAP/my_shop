package ru.perelyginva.myproject.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.perelyginva.myproject.data.models.CartModel


 @Database(entities = [CartModel::class], version = 1)
abstract class CardDB: RoomDatabase() {

    abstract val cartDao: CartDao
}