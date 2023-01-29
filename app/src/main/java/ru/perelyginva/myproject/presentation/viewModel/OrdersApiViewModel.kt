package ru.perelyginva.myproject.presentation.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.perelyginva.myproject.domain.useCase.OrdersApiUseCase

class OrdersApiViewModel(private val ordersApiUseCase: OrdersApiUseCase): ViewModel()  {

    fun insert(
        name:String,
        phone:String,
        descriptions:String,
        priceOrder:String) = viewModelScope.launch {

        ordersApiUseCase.insert(name, phone, descriptions, priceOrder)
    }

}