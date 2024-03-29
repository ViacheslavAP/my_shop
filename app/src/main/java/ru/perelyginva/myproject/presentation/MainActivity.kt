package ru.perelyginva.myproject.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.myproject.R
import ru.perelyginva.myproject.databinding.ActivityMainBinding
import ru.perelyginva.myproject.presentation.tabs.Account
import ru.perelyginva.myproject.presentation.tabs.Cart
import ru.perelyginva.myproject.presentation.tabs.Food
import ru.perelyginva.myproject.presentation.tabs.Home
import ru.perelyginva.myproject.presentation.viewModel.CartViewModel
import ru.perelyginva.myproject.presentation.viewModel.FoodViewModel

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val foodViewModel: FoodViewModel by viewModel()
    private val cartViewModel: CartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        foodViewModel.migrations(this)
        supportFragmentManager.beginTransaction().replace(R.id.content, Home()).commit()
        loadFoodToCart()

        binding?.bottomMainMenu?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, Home()).commit()
                }

                R.id.food_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, Food()).commit()
                }

                R.id.cart_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, Cart()).commit()
                }

                R.id.account_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.content, Account()).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun loadFoodToCart() {

        cartViewModel.loadFoodFromCart.observe(this, Observer {
            val count = it.count()
            val badge = binding?.bottomMainMenu?.getOrCreateBadge(R.id.cart_menu)
            badge?.isVisible = count > 0
            badge?.number = count
        })
    }
}