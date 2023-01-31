package ru.perelyginva.myproject.presentation.tabs

import android.app.Activity
import  android.os.Bundle
import android.util.Patterns
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.myproject.R
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
//TODO("не корректно работает проверка на не правильный ввод данных")
        phoneFocusListener()


        binding?.submitCheckOut?.setOnClickListener(View.OnClickListener {

            cartViewModel.loadFoodFromCart.observe(viewLifecycleOwner, Observer {

                val totalOrder: Int = it.sumOf { it.totalPrice.toInt() }

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

            correctDataFormat()

        })



        return binding?.root
    }

    private fun correctDataFormat() {
        val validName = binding?.layoutEnterNameCheckout?.helperText == null
        val validPhone = binding?.layoutEnterPhoneCheckout?.helperText == null

        if (validName && validPhone) {
            resetForm()
        } else
            invalidForm()

    }

    private fun invalidForm() {

        var message = ""
        if (binding?.layoutEnterNameCheckout?.helperText == null)
            message += "\n\nИмя:" + binding?.layoutEnterNameCheckout?.helperText

        if (binding?.layoutEnterPhoneCheckout?.helperText == null)
            message += "\n\nТелефон:" + binding?.layoutEnterPhoneCheckout?.helperText

        AlertDialog.Builder((context as FragmentActivity))
            .setTitle("Неправильная форма!")
            .setMessage(message)
            .setPositiveButton("Хорошо") { _, _ -> }
            .show()
    }

    private fun resetForm() {
        var message = ""
        message += "\nИмя: " + binding?.enterNameCheckout?.text
        message += "\nТелефон: " + binding?.enterPhoneCheckout?.text

        AlertDialog.Builder((context as FragmentActivity))
            .setTitle("Правильная форма!")
            .setMessage(message)
            .setPositiveButton("Хорошо") { _, _ ->

                binding?.enterNameCheckout?.text = null
                binding?.enterPhoneCheckout?.text = null

                binding?.layoutEnterNameCheckout?.helperText = getText(R.string.required)
                binding?.layoutEnterPhoneCheckout?.helperText = getText(R.string.required)

            }.show()
    }

    private fun phoneFocusListener() {

        binding?.enterPhoneCheckout?.setOnFocusChangeListener { _, focused ->

            if (!focused) {
                binding?.layoutEnterPhoneCheckout?.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = binding?.enterPhoneCheckout?.text.toString()
        if (phoneText.length != 10) {
            return "введен не корректный номер"
        }
        return null
    }


}