package ru.perelyginva.myproject.domain.useCase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.domain.repository.CartCall

class CartUseCase(private val cartCall: CartCall) {

     suspend fun insert(cartModel: CartModel){
        cartCall .insert(cartModel)
    }

     fun loadFoodFromCart(): LiveData<List<CartModel>> {
        return cartCall .loadFoodFromCart()
    }

     fun loadFoodToCartFromCartProduct(idProduct: String): LiveData<List<CartModel>> {
        return cartCall .loadFoodToCartFromCartProduct(idProduct)
    }

    suspend  fun updateProductFromCart(cartModel: CartModel){
        CoroutineScope(Dispatchers.IO).launch {
            cartCall.updateProductFromCart(cartModel)
        }
    }

    suspend fun deleteProductFromCart(idProduct: Int){
        return cartCall.deleteProductFromCart(idProduct)
    }

    suspend fun deleteProductToCartFromCardProduct(idProduct: String){
        return cartCall.deleteProductToCartFromCardProduct(idProduct)
    }

     suspend fun clear(){
        cartCall.clear()
    }
}