package ru.perelyginva.myproject.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.perelyginva.myproject.presentation.di.cart
import ru.perelyginva.myproject.presentation.di.food
import ru.perelyginva.myproject.presentation.di.order
import ru.perelyginva.myproject.presentation.di.orderApi

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger()

            androidContext(this@App)

            modules(food, cart, order, orderApi)
        }
    }
}