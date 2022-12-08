package ru.perelyginva.myproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.perelyginva.myproject.data.models.FoodModel
import ru.perelyginva.myproject.databinding.FoodItemBinding

class FoodAdapter(
    private val addToCart: (FoodModel) -> Unit,
    private val removeFromCart: (FoodModel) -> Unit,
    private val loadFoodToCartFromCartProduct: (Int, AppCompatImageButton, AppCompatImageButton) -> Unit) :

    RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    private val food = ArrayList<FoodModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {

        val binding: FoodItemBinding = FoodItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return FoodHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {

        holder.bind(
            food[position],
            addToCart,
            removeFromCart,
            loadFoodToCartFromCartProduct)
    }

    override fun getItemCount(): Int {

        return food.size
    }

    fun setList(foodList: List<FoodModel>) {

        food.clear()
        food.addAll(foodList)
    }

    class FoodHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            foodModel: FoodModel, addToCart: (FoodModel) -> Unit,
            removeFromCart: (FoodModel) -> Unit,
            loadFoodToCartFromCartProduct: (Int, AppCompatImageButton, AppCompatImageButton) -> Unit
        ){

            val getImage = foodModel.image
            Picasso.get().load(getImage).into(binding.imageFood)
            binding.nameFood.text = foodModel.name
            binding.descriptionFood.text = foodModel.descriptions
            binding.priceCoffee.text = foodModel.price

            binding.addToCart.setOnClickListener(View.OnClickListener {
                addToCart(foodModel)
            })

            binding.removeFromCart .setOnClickListener(View.OnClickListener {
                removeFromCart (foodModel)
            })

            loadFoodToCartFromCartProduct(foodModel.id, binding.addToCart, binding.removeFromCart)
        }
    }
}