package ru.perelyginva.myproject.data.dataSource

import androidx.lifecycle.LiveData
import ru.perelyginva.myproject.data.models.FoodModel

interface FoodDataSource {

    fun insert(foodModel: FoodModel)

    fun loadFood(): LiveData<List<FoodModel>>

    suspend fun clear()

}