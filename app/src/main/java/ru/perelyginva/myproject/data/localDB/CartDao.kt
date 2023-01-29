package ru.perelyginva.myproject.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.perelyginva.myproject.data.models.CartModel

@Dao
interface CartDao {
    //добавляем в бд
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(cartModel: CartModel)

    //получаем из б
    @Query("SELECT * FROM cart_data_table")
    fun loadFoodFromCart(): LiveData<List<CartModel>>

    @Query("SELECT * FROM cart_data_table WHERE cart_idProduct =:idProduct")
    fun loadFoodToCartFromCartProduct(idProduct: String): LiveData<List<CartModel>>

    @Update
    suspend fun updateProductFromCart(cartModel: CartModel)

    @Query("DELETE  FROM cart_data_table WHERE cart_id =:idProduct")
    suspend fun deleteProductFromCart(idProduct: Int)

    @Query("DELETE  FROM cart_data_table WHERE cart_idProduct =:idProduct")
    suspend fun deleteProductToCartFromCardProduct(idProduct: String)

    //очищаем бд
    @Query("DELETE FROM cart_data_table")
   suspend fun clear( )
}