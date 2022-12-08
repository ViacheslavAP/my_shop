package ru.perelyginva.myproject.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.perelyginva.myproject.domain.useCase.FoodUseCase

class FoodViewModel(private val foodUseCase: FoodUseCase): ViewModel()  {

    val loadFood = foodUseCase.loadFood()

    fun migrations(context: Context) = viewModelScope.launch {

        foodUseCase.startMigration(context)
    }
}