package ru.perelyginva.myproject.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import ru.perelyginva.myproject.data.dataSource.FoodApiDataSource
import ru.perelyginva.myproject.data.dataSource.FoodDataSource
import ru.perelyginva.myproject.data.models.FoodModel
import ru.perelyginva.myproject.domain.repository.FoodCall

class FoodRepository(
    private val foodApiDataSource: FoodApiDataSource,
    private val foodDataSource: FoodDataSource,
) : FoodCall {

    override fun loadFood(): LiveData<List<FoodModel>>{
        return foodDataSource.loadFood()
    }

    override suspend fun startMigration(context: Context){
        foodDataSource.clear()
        foodApiDataSource.startMigration(context)
    }
}