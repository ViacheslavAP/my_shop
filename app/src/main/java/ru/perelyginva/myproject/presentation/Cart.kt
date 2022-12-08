package ru.perelyginva.myproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.myproject.R
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.databinding.FragmentCartBinding
import ru.perelyginva.myproject.presentation.adapter.CartAdapter
import ru.perelyginva.myproject.presentation.viewModel.CartViewModel


class Cart : Fragment(), View.OnClickListener {

    private var binding: FragmentCartBinding? = null
    private var cartAdapter: CartAdapter? = null
    private val cartViewModel: CartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false)

        initRecyclerCart()
        loadFoodFromCart()
        //очищаем корзину
        binding?.clearCart?.setOnClickListener(this)
        binding?.checkoutCart?.setOnClickListener(this)
        return binding?.root
    }


    private fun initRecyclerCart() {

        binding?.listCart?.layoutManager = LinearLayoutManager(context)
        cartAdapter = CartAdapter ({ cartModel: CartModel -> deleteFromCart(cartModel) },
            { cartModel: CartModel -> lessCount(cartModel) } ,

            { cartModel: CartModel -> moreCount(cartModel) }
            )
        binding?.listCart?.adapter = cartAdapter

    }

    private fun loadFoodFromCart() {

        cartViewModel.loadFoodFromCart.observe(viewLifecycleOwner, Observer {
            cartAdapter?.setList(it)
            cartAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteFromCart(cartModel: CartModel) {

        cartViewModel.deleteProductFromCart(cartModel.id)
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.clearCart -> cartViewModel.clearCart()

            R.id.checkoutCart -> {

            }
        }
    }

    private fun lessCount(cartModel: CartModel) {

        var count: Int = cartModel.count.toInt()

        count--

        if (count < 1) {

            cartViewModel.updateProductFromCart(CartModel(
                cartModel.id,
                cartModel.name,
                cartModel.image,
                cartModel.price,
                cartModel.idProduct,
                "1"))
        } else {

            cartViewModel.updateProductFromCart(CartModel(
                cartModel.id,
                cartModel.name,
                cartModel.image,
                cartModel.price,
                cartModel.idProduct,
                count.toString()))
        }
    }

    private fun moreCount(cartModel: CartModel) {

        var count: Int = cartModel.count.toInt()

        count++

        cartViewModel.updateProductFromCart(CartModel(
            cartModel.id,
            cartModel.name,
            cartModel.image,
            cartModel.price,
            cartModel.idProduct,
            count.toString()))
    }
}