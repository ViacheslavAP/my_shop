package ru.perelyginva.myproject.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.perelyginva.myproject.data.models.FoodModel

@Database(entities = [FoodModel::class], version = 1)
abstract class FoodDB: RoomDatabase() {

abstract val foodDao: FoodDao
}