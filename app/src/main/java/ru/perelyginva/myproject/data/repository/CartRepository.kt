package ru.perelyginva.myproject.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.perelyginva.myproject.data.localDB.CartDao
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.domain.repository.CartCall

class CartRepository(private val dao: CartDao): CartCall {

    override suspend fun insert(cartModel: CartModel){
         dao.insert(cartModel)
    }

    override fun loadFoodFromCart(): LiveData<List<CartModel>>{
        return dao.loadFoodFromCart()
    }

    override fun loadFoodToCartFromCartProduct(idProduct: String): LiveData<List<CartModel>>{
        return dao.loadFoodToCartFromCartProduct(idProduct)
    }
//обновление позиции товара
    override suspend  fun updateProductFromCart(cartModel: CartModel){

        dao.updateProductFromCart(cartModel)
    }

    override suspend fun deleteProductFromCart(idProduct: Int){

        CoroutineScope(Dispatchers.IO).launch {

            dao.deleteProductFromCart(idProduct)
        }
    }

  override suspend fun deleteProductToCartFromCardProduct(idProduct: String){

      CoroutineScope(Dispatchers.IO).launch {

          dao.deleteProductToCartFromCardProduct(idProduct)
      }
  }

    override suspend fun clear(){
         dao.clear()
    }
}