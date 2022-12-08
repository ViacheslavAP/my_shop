package ru.perelyginva.myproject.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class  FoodApiModel(
    @SerializedName("id") @Expose
    var id: Int? = null,

    @SerializedName("image") @Expose
    var image: String? = null,

    @SerializedName("name") @Expose
    var name: String? = null,

    @SerializedName("descriptions") @Expose
    var descriptions: String? = null,

    @SerializedName("price") @Expose
    var price: String? = null,
)