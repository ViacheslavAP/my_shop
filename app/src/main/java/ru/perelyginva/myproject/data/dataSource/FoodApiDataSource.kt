package ru.perelyginva.myproject.data.dataSource

import android.content.Context

interface FoodApiDataSource  {

    fun startMigration (context: Context)
}