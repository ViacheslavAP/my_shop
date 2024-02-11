package ru.perelyginva.myproject.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_data_table")
data class FoodModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id")
    var id: Int,
    @ColumnInfo(name = "food_image")
    var image: String,
    @ColumnInfo(name = "food_name")
    var name: String,
    @ColumnInfo(name = "food_descriptions")
    var descriptions: String,
    @ColumnInfo(name = "food_price")
    var price: String,
)