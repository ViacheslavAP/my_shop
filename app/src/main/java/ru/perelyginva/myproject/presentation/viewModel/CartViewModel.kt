package ru.perelyginva.myproject.presentation.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.domain.useCase.CartUseCase


class CartViewModel(private val cartUseCase: CartUseCase) : ViewModel() {

    val loadFoodFromCart = cartUseCase.loadFoodFromCart()

    fun startInsert(
        imageFood: String,
        nameFood: String,
        priceFood: String,
        idProduct: String,
        countFood: String,
    ) {
        insert(CartModel(0, imageFood, nameFood, priceFood, idProduct, countFood))
    }

    private fun insert(cartModel: CartModel) = viewModelScope.launch {

        cartUseCase.insert(cartModel)
    }

    fun loadFoodToCartFromCartProduct(idProduct: String): LiveData<List<CartModel>> {

        return cartUseCase.loadFoodToCartFromCartProduct(idProduct)
    }

    //удаляет товар из корзины
    fun deleteProductFromCart(idProduct: Int) = viewModelScope.launch {

        cartUseCase.deleteProductFromCart(idProduct)
    }

    //удаляет из карзины, если мы обращаемся к товару из карточки
    fun deleteProductToCartFromCartProduct(idProduct: String) = viewModelScope.launch {

        cartUseCase.deleteProductToCartFromCardProduct(idProduct)
    }

    fun clearCart() = viewModelScope.launch {

        cartUseCase.clear()
    }
}