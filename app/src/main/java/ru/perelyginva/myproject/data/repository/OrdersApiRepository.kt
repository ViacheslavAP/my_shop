package ru.perelyginva.myproject.data.repository


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import ru.perelyginva.myproject.data.api.ApiClient
import ru.perelyginva.myproject.domain.repository.OrdersApiCall

class OrdersApiRepository : OrdersApiCall {

    override fun insert(
        name: String,
        phone: String,
        descriptions: String,
        priceOrder: String,
    ) {

        val callInsertCategory: Call<ResponseBody?>? = ApiClient.instance?.api?.insert(
            name,
            phone,
            descriptions,
            priceOrder
        )
        callInsertCategory?.enqueue(object : retrofit2.Callback<ResponseBody?> {

            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                //если произошла ошибка передачи данных, пробудем передать данные снова
                insert(name, phone, descriptions, priceOrder)
            }
        })


    }
}