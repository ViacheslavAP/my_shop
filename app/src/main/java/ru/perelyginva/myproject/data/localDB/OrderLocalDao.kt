package ru.perelyginva.myproject.data.localDB

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.perelyginva.myproject.data.models.OrderLocalModel

interface OrderLocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(orderLocalModel: OrderLocalModel)

}