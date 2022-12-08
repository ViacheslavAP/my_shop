package ru.perelyginva.myproject.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import ru.perelyginva.myproject.data.models.FoodModel
import ru.perelyginva.myproject.domain.repository.FoodCall

class FoodUseCase(private val foodCall: FoodCall) {

    fun loadFood(): LiveData<List<FoodModel>>{

        return foodCall.loadFood()
    }

    suspend fun startMigration(context: Context){

        foodCall.startMigration(context)
    }
}