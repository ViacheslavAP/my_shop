package ru.perelyginva.myproject.presentation.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
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
    private val ordersApiViewModel: OrdersApiViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)



        binding?.submitCheckOut?.setOnClickListener(View.OnClickListener {

//проверяю ввел пользователь данные или нет! &&

            if (binding?.enterNameCheckout?.text.toString().isEmpty()
            ) {
                binding?.layoutEnterNameCheckout?.error = "Введите Имя"
                return@OnClickListener
            } else if (binding?.enterPhoneCheckout?.text?.length!! < 10) {

                binding?.layoutEnterPhoneCheckout?.error = "Введите номер телефона"
                return@OnClickListener
            } else {

                AlertDialog.Builder((context as FragmentActivity))
                    .setTitle("Заявка оформлена!")
                    .setPositiveButton("Хорошо") { _, _ -> }
                    .show()

            }
            cartViewModel.loadFoodFromCart.observe(viewLifecycleOwner, Observer {

                val totalOrder: Int = it.sumOf { it.totalPrice.toInt() }
//TODO("добавить символ рубля")
                val descriptionOrder = it.map {
                    it.name + ": count - " + it.count + ": price - " + it.totalPrice + ": \nU+20BD\n;"
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

                //выводим сообщение о том, что карзина пуста
                if (descriptionOrderAccount.isEmpty()) {
                    binding?.submitCheckOut?.visibility = View.GONE
                    Toast.makeText(
                        (context as FragmentActivity),
                        "Вы ни чего не выбрали!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ordersApiViewModel.insert(
                    binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(),
                    descriptionOrderAccount,
                    totalOrder.toString()
                )

                binding?.enterNameCheckout?.setText("")
                binding?.enterPhoneCheckout?.setText("")

                dismiss()

                cartViewModel.clearCart()
            })

        })

        return binding?.root
    }

}