package ru.perelyginva.myproject.data.api

import android.telecom.CallScreeningService.CallResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import ru.perelyginva.myproject.data.models.FoodApiModel

interface ApiInterface {

    @GET("loadFood.php")
    fun loadFoodApi(): Call<ArrayList<FoodApiModel>>

    @FormUrlEncoded
    @POST("insert.php")
    fun insert(
        @Field ("name") name: String?,
        @Field ("phone") phone: String?,
        @Field("descriptions") descriptions: String?,
        @Field("priceOrder") priceOrder: String?,
    ): Call<ResponseBody?>?
}