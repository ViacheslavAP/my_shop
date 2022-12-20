package ru.perelyginva.myproject.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OrdersApiModel(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("phone")
    @Expose
    var phone: String? = null,

    @SerializedName("descriptions")
    @Expose
    var descriptions: String? = null,

    @SerializedName("priceOrder")
    @Expose
    var priceOrder: String? = null,
)