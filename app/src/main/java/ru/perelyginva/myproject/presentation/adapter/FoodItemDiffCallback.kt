package ru.perelyginva.myproject.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.perelyginva.myproject.data.models.FoodModel

class FoodItemDiffCallback: DiffUtil.ItemCallback<FoodModel>() {

    override fun areItemsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodModel, newItem: FoodModel): Boolean {
        return  oldItem == newItem
    }
}