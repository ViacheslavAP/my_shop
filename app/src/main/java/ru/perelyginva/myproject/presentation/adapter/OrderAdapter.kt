package ru.perelyginva.myproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.perelyginva.myproject.data.models.OrderLocalModel
import ru.perelyginva.myproject.databinding.OrderItemBinding

class  OrderAdapter() :
    RecyclerView.Adapter<OrderAdapter.CheckoutHolder>() {

    private val orders = ArrayList<OrderLocalModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutHolder {
        val binding =
            OrderItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)

        return CheckoutHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckoutHolder, position: Int) {
        holder.bind(orders[position])

    }

    override fun getItemCount(): Int {

        return orders.size
    }

    fun setList(orderList: List<OrderLocalModel>) {

        orders.clear()
        orders.addAll(orderList)
    }

    class CheckoutHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(orderLocalModel: OrderLocalModel) {

            binding.apply {
                nameUserCheckOut.text = orderLocalModel.nameUser
                phoneUserCheckOut.text = orderLocalModel.phoneUser
                descriptionCheckout.text = orderLocalModel.description
                totalCheckout.text = orderLocalModel.totalPrice
            }
        }
    }
}