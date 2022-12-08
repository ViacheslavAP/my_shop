package ru.perelyginva.myproject.data.api

import retrofit2.Call
import retrofit2.http.GET
import ru.perelyginva.myproject.data.models.FoodApiModel

interface ApiInterface {

    @GET("loadFood.php")
    fun loadFoodApi(): Call<ArrayList<FoodApiModel>>
}