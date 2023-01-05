package ru.perelyginva.myproject.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.myproject.R
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.databinding.FragmentCheckoutBinding
import ru.perelyginva.myproject.presentation.viewModel.CartViewModel
import ru.perelyginva.myproject.presentation.viewModel.OrderLocalViewModel
import ru.perelyginva.myproject.presentation.viewModel.OrdersApiViewModel


class Checkout : BottomSheetDialogFragment() {

    private var binding: FragmentCheckoutBinding? = null
    private val orderLocalViewModel: OrderLocalViewModel by viewModel()
    private val cartViewModel: CartViewModel by viewModel()
    private val  ordersApiViewModel: OrdersApiViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)

        binding?.submitCheckOut?.setOnClickListener(View.OnClickListener {

            cartViewModel.loadFoodFromCart.observe(viewLifecycleOwner, Observer {

                val totalOrder: Int = it.sumOf<CartModel> { it.totalPrice.toInt() }

                val descriptionOrder = it.map {
                    it.name + ": count - " + it.count + ": price - " + it.totalPrice + ": $;"
                }.joinToString()

                orderLocalViewModel.startInsert(
                    binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(),
                    descriptionOrder,
                    totalOrder.toString()
                )

                ordersApiViewModel.insert(
                    binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(),
                    descriptionOrder,
                    totalOrder.toString())

                binding?.enterNameCheckout?.setText("")
                binding?.enterPhoneCheckout?.setText("")
                dismiss()

                (context as FragmentActivity)
                    .supportFragmentManager.beginTransaction()
                    .replace(R.id.listOrders, Account()).commit()
                //очитаем базу данных корзины после того как сформировали заказ
                cartViewModel.clearCart()
            })

        })

        return binding?.root
    }


}