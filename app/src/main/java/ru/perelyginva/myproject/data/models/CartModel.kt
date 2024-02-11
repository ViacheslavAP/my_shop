package ru.perelyginva.myproject.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_data_table")
data class CartModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_id")
    var id: Int,
    @ColumnInfo(name = "cart_image")
    var image: String,
    @ColumnInfo(name = "cart_name")
    var name: String,
    @ColumnInfo(name = "cart_price")
    var price: String,
    @ColumnInfo(name = "cart_idProduct")
    var idProduct: String,
    @ColumnInfo(name = "cart_count")
    var count: String,
    @ColumnInfo(name = "cart_totalPrice")
    var totalPrice: String,
)