package ru.perelyginva.myproject.data.dataSourceIMPL

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.perelyginva.myproject.R
import ru.perelyginva.myproject.data.api.ApiClient
import ru.perelyginva.myproject.data.dataSource.FoodApiDataSource
import ru.perelyginva.myproject.data.dataSource.FoodDataSource
import ru.perelyginva.myproject.data.models.FoodApiModel
import ru.perelyginva.myproject.data.models.FoodModel

class FoodApiDataSourceIMPL(private val foodDataSource: FoodDataSource) : FoodApiDataSource {

    override fun startMigration(context: Context) {

        val call = ApiClient.instance?.api?.loadFoodApi()
        call?.enqueue(object : Callback<ArrayList<FoodApiModel>> {

            override fun onResponse(
                call: Call<ArrayList<FoodApiModel>>,
                response: Response<ArrayList<FoodApiModel>>,
            ) {
                var loadFood: ArrayList<FoodApiModel>? = null
                loadFood?.clear()
                loadFood = response.body()!!
                for (audit in loadFood) {
                    audit.id?.let {
                        FoodModel(
                            it,
                            audit.image.toString(),
                            audit.name.toString(),
                            audit.descriptions.toString(),
                            audit.price.toString()
                        )
                    }?.let {
                        foodDataSource.insert(it)
                    }
                }
                Toast.makeText(context, R.string.load_text, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ArrayList<FoodApiModel>>, t: Throwable) {
                //TODO("Заменить тост на диалоги")
                Toast.makeText(
                    context, R.string.error_connections, Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}