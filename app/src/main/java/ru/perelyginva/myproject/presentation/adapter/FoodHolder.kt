package ru.perelyginva.myproject.presentation.adapter

import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.perelyginva.myproject.data.models.FoodModel
import ru.perelyginva.myproject.databinding.FoodItemBinding

class FoodHolder(private val binding: FoodItemBinding) : RecyclerView
.ViewHolder(binding.root) {
    fun bind(
        foodModel: FoodModel,
        addToCart: (FoodModel) -> Unit,
        removeFromCart: (FoodModel) -> Unit,
        loadFoodToCartFromCartProduct: (
            Int,
            AppCompatImageButton,
            AppCompatImageButton,
        ) -> Unit,
    ) {
        val getImage = foodModel.image
        Picasso.get().load(getImage).into(binding.imageFood)
        binding.nameFood.text = foodModel.name
        binding.descriptionFood.text = foodModel.descriptions
        binding.priceCoffee.text = foodModel.price
        binding.addToCart.setOnClickListener {
            addToCart(foodModel)
        }
        binding.removeFromCart.setOnClickListener {
            removeFromCart(foodModel)
        }
        binding.cardFood.setOnClickListener {
            binding.descriptionFood.apply {
                isVisible = !isVisible
            }
        }
        loadFoodToCartFromCartProduct(foodModel.id, binding.addToCart, binding.removeFromCart)
    }
}