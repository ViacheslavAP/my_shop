package ru.perelyginva.myproject.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_local_data_table")
data class OrderLocalModel(

    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "order_local_id")
    var id: Int,

    @ColumnInfo(name = "order_local_nameUser")
    var nameUser: String,

    @ColumnInfo(name = "order_local_phoneUser")
    var phoneUser: String,

    @ColumnInfo(name = "order_localDescription")
    var description: String,

    @ColumnInfo(name = "order_totalPrice")
    var totalPrice: String,
)