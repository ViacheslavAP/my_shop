package ru.perelyginva.myproject.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.perelyginva.myproject.data.models.FoodModel

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(foodModel: FoodModel)

    @Query("SELECT * FROM food_data_table")
    fun loadFood(): LiveData<List<FoodModel>>

    @Query("DELETE FROM food_data_table")
    suspend fun clear()
}