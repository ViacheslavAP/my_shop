package ru.perelyginva.myproject.data.dataSourceIMPL

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.perelyginva.myproject.data.dataSource.FoodDataSource
import ru.perelyginva.myproject.data.localDB.FoodDao
import ru.perelyginva.myproject.data.models.FoodModel

class FoodDataSourceIMPL(private val dao: FoodDao):FoodDataSource {

    override fun insert(foodModel: FoodModel){

        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(foodModel)
        }
    }

    override fun loadFood(): LiveData<List<FoodModel>>{
        return dao.loadFood()
    }

    override suspend fun clear(){

        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()
        }
    }
}