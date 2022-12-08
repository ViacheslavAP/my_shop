package ru.perelyginva.myproject.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import ru.perelyginva.myproject.data.models.FoodModel

interface FoodCall {

    fun loadFood(): LiveData<List<FoodModel>>

    suspend fun startMigration(context: Context)
}