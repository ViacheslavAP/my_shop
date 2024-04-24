package ru.perelyginva.myproject.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.perelyginva.myproject.data.dataSource.FoodApiDataSource
import ru.perelyginva.myproject.data.dataSource.FoodDataSource
import ru.perelyginva.myproject.data.dataSourceIMPL.FoodApiDataSourceIMPL
import ru.perelyginva.myproject.data.dataSourceIMPL.FoodDataSourceIMPL
import ru.perelyginva.myproject.data.localDB.DatabaseFood
import ru.perelyginva.myproject.data.repository.CartRepository
import ru.perelyginva.myproject.data.repository.FoodRepository
import ru.perelyginva.myproject.data.repository.OrdersApiRepository
import ru.perelyginva.myproject.data.repository.OrderLocalRepository
import ru.perelyginva.myproject.domain.repository.CartCall
import ru.perelyginva.myproject.domain.repository.FoodCall
import ru.perelyginva.myproject.domain.repository.OrderLocalCall
import ru.perelyginva.myproject.domain.repository.OrdersApiCall
import ru.perelyginva.myproject.domain.useCase.CartUseCase
import ru.perelyginva.myproject.domain.useCase.FoodUseCase
import ru.perelyginva.myproject.domain.useCase.OrderLocalUseCase
import ru.perelyginva.myproject.domain.useCase.OrdersApiUseCase
import ru.perelyginva.myproject.presentation.viewModel.CartViewModel
import ru.perelyginva.myproject.presentation.viewModel.FoodViewModel
import ru.perelyginva.myproject.presentation.viewModel.OrderLocalViewModel
import ru.perelyginva.myproject.presentation.viewModel.OrdersApiViewModel

val food = module {

    single { Room.databaseBuilder(androidContext(), DatabaseFood::class.java, "foodDB").build() }

    single { get<DatabaseFood>().foodDao }

    single<FoodDataSource> { FoodDataSourceIMPL(get()) }

    single<FoodApiDataSource> { FoodApiDataSourceIMPL(get()) }

    single<FoodCall> { FoodRepository(get(), get()) }

    single { FoodUseCase(get()) }

    viewModel { FoodViewModel(get()) }
}

val cart = module {

    single { Room.databaseBuilder(androidContext(), DatabaseFood::class.java, "cartDB").build() }

    single { get<DatabaseFood>().cartDao }

    single<CartCall> { CartRepository(get()) }

    single { CartUseCase(get()) }

    viewModel { CartViewModel(get()) }
}

val order = module {

    single { Room.databaseBuilder(androidContext(), DatabaseFood::class.java, "orderDB").build() }

    single { get<DatabaseFood>().orderLocalDao }

    single<OrderLocalCall> { OrderLocalRepository(get()) }

    single { OrderLocalUseCase(get()) }

    viewModel { OrderLocalViewModel(get()) }
}

val orderApi = module {

    single<OrdersApiCall> { OrdersApiRepository() }

    single { OrdersApiUseCase(get()) }

    viewModel { OrdersApiViewModel(get()) }
}