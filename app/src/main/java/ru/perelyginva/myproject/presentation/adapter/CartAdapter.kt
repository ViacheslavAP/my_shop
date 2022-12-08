package ru.perelyginva.myproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.databinding.CartItemBinding

class CartAdapter(
    private val deleteFromCart: (CartModel) -> Unit,
    private val lessCount: (CartModel) -> Unit,
    private val moreCount: (CartModel) -> Unit,
) :
    RecyclerView.Adapter<CartAdapter.CartHolder>() {

    private val productsFromCart = ArrayList<CartModel>()//получаем содержимое карзины

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val binding =
            CartItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)

        return CartHolder(binding)
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        holder.bind(productsFromCart[position], deleteFromCart, lessCount, moreCount)
    }

    override fun getItemCount(): Int {

        return productsFromCart.size
    }

    fun setList(cartList: List<CartModel>) {

        productsFromCart.clear()
        productsFromCart.addAll(cartList)
    }

    class CartHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            cartModel: CartModel, deleteFromCart: (CartModel) -> Unit,
            lessCount: (CartModel) -> Unit,
            moreCount: (CartModel) -> Unit,
        ) {

            val getImage = cartModel.image
            Picasso.get().load(getImage).into(binding.imageProductCart)
            binding.nameProductCart.text = cartModel.name
            binding.priceProductCart.text = cartModel.price
            binding.countProductCart.text = cartModel.count

            binding.removeFromCartProductCart.setOnClickListener(View.OnClickListener {

                deleteFromCart(cartModel)
            })

            binding.moreProductBasket.setOnClickListener(View.OnClickListener {

                moreCount(cartModel)
            })

            binding.lessProductBasket.setOnClickListener(View.OnClickListener {
                lessCount(cartModel)
            })
        }
    }
}