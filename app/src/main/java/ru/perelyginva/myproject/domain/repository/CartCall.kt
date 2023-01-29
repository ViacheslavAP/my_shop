package ru.perelyginva.myproject.domain.repository

import androidx.lifecycle.LiveData
import ru.perelyginva.myproject.data.models.CartModel

interface CartCall {

    suspend fun insert(cartModel: CartModel)

    fun loadFoodFromCart(): LiveData<List<CartModel>>

    fun loadFoodToCartFromCartProduct(idProduct: String): LiveData<List<CartModel>>

    suspend  fun updateProductFromCart(cartModel: CartModel)

    suspend fun deleteProductFromCart(idProduct: Int)

    suspend fun deleteProductToCartFromCardProduct(idProduct: String)

    suspend fun clear()
}