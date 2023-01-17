package ru.perelyginva.myproject.presentation.tabs

import  android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
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

                val totalOrder: Int = it.sumOf { it.totalPrice.toInt() }

                val descriptionOrder = it.map {
                    it.name + ": count - " + it.count + ": price - " + it.totalPrice + ": $;"
                }.joinToString("")

                orderLocalViewModel.startInsert(
                    binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(),
                    descriptionOrder,
                    totalOrder.toString()
                )

                val descriptionOrderAccount = it.map {
                    it.name + " - " + it.count + " - " + it.totalPrice
                }.joinToString()

                ordersApiViewModel.insert(
                    binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(),
                    descriptionOrderAccount,
                    totalOrder.toString())

                binding?.enterNameCheckout?.setText("")
                binding?.enterPhoneCheckout?.setText("")

                dismiss()

                cartViewModel.clearCart()
            })

        })

        return binding?.root
    }


}