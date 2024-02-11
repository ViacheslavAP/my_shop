package ru.perelyginva.myproject.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.perelyginva.myproject.data.models.FoodModel
import ru.perelyginva.myproject.databinding.FoodItemBinding

class FoodAdapter(
    private val addToCart: (FoodModel) -> Unit,
    private val removeFromCart: (FoodModel) -> Unit,
    private val loadFoodToCartFromCartProduct: (Int, AppCompatImageButton, AppCompatImageButton) -> Unit,
) : ListAdapter<FoodModel, FoodHolder>(FoodItemDiffCallback()) {

//    private val food = ArrayList<FoodModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {

        val binding: FoodItemBinding = FoodItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FoodHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(
            getItem(position),
            addToCart,
            removeFromCart,
            loadFoodToCartFromCartProduct
        )
    }

//    fun setList(foodList: List<FoodModel>) {
//        food.clear()
//        food.addAll(foodList)
//    }
}