package ru.perelyginva.myproject.presentation.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.perelyginva.myproject.data.dataSource.FoodApiDataSource
import ru.perelyginva.myproject.data.dataSource.FoodDataSource
import ru.perelyginva.myproject.data.dataSourceIMPL.FoodApiDataSourceIMPL
import ru.perelyginva.myproject.data.dataSourceIMPL.FoodDataSourceIMPL
import ru.perelyginva.myproject.data.localDB.CartDB
import ru.perelyginva.myproject.data.localDB.FoodDB
import ru.perelyginva.myproject.data.repository.CartRepository
import ru.perelyginva.myproject.data.repository.FoodRepository
import ru.perelyginva.myproject.domain.repository.CartCall
import ru.perelyginva.myproject.domain.repository.FoodCall
import ru.perelyginva.myproject.domain.useCase.CartUseCase
import ru.perelyginva.myproject.domain.useCase.FoodUseCase
import ru.perelyginva.myproject.presentation.viewModel.CartViewModel
import ru.perelyginva.myproject.presentation.viewModel.FoodViewModel

val food = module {

    single { Room.databaseBuilder(androidContext(), FoodDB::class.java, "foodDB").build() }

    single { get <FoodDB>().foodDao }

    single<FoodDataSource> { FoodDataSourceIMPL(get())}

    single<FoodApiDataSource> { FoodApiDataSourceIMPL(get()) }

    single<FoodCall> { FoodRepository(get(), get()) }

    single { FoodUseCase(get()) }

    viewModel { FoodViewModel(get()) }
}

val cart  = module {

    single { Room.databaseBuilder(androidContext(), CartDB::class.java, "cartDB").build() }

    single { get <CartDB>().cartDao }

    single<CartCall> { CartRepository(get()) }

    single { CartUseCase(get()) }

    viewModel { CartViewModel(get()) }
}