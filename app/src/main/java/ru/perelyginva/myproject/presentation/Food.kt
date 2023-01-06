package ru.perelyginva.myproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.myproject.data.models.FoodModel
import ru.perelyginva.myproject.databinding.FragmentFoodBinding
import ru.perelyginva.myproject.presentation.adapter.FoodAdapter
import ru.perelyginva.myproject.presentation.viewModel.CartViewModel
import ru.perelyginva.myproject.presentation.viewModel.FoodViewModel


class Food : Fragment() {

    private var binding: FragmentFoodBinding? = null
    private var foodAdapter: FoodAdapter? = null
    private val foodViewModel: FoodViewModel by viewModel()
    private val cartViewModel: CartViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFoodBinding.inflate(layoutInflater, container, false)

        initRecyclerFood()
        loafFood()

        return binding?.root
    }

    private fun initRecyclerFood() {

        binding?.recyclerFood?.layoutManager = LinearLayoutManager(context)
        foodAdapter = FoodAdapter({ foodModel: FoodModel ->
            addToCart(foodModel)
        },
            { foodModel: FoodModel ->
                removeFromCart(foodModel)
            },

            { idProduct: Int,
              addToBasket: AppCompatImageButton,
              removeFromBasket: AppCompatImageButton ->
                loadFoodToCartFromCartProduct(idProduct, addToBasket, removeFromBasket)
            })

        binding?.recyclerFood?.adapter = foodAdapter
    }

    private fun loafFood() {
        foodViewModel.loadFood.observe(viewLifecycleOwner, Observer {

            foodAdapter?.setList(it)
            foodAdapter?.notifyDataSetChanged()
        })
    }

    private fun addToCart(foodModel: FoodModel) {

        cartViewModel.startInsert(
            foodModel.image,
            foodModel.name,
            foodModel.price,
            foodModel.id.toString(),
            "1")
    }

    private fun removeFromCart(foodModel: FoodModel) {

        cartViewModel.deleteProductToCartFromCartProduct(
            foodModel.id.toString())
    }

    private fun loadFoodToCartFromCartProduct(
        idProduct: Int,
        addToBasket: AppCompatImageButton,
        removeFromBasket: AppCompatImageButton,
    ) {
        //проверяем если ли что то в карзине и если нет, то в карточке показываем
        //кнопку добавить, если что то есть, то кнопку удалить
        cartViewModel.loadFoodToCartFromCartProduct(idProduct.toString())
            .observe(viewLifecycleOwner, Observer {

                val count = it.count()
                if (count > 0) {

                    addToBasket.visibility = View.GONE
                    removeFromBasket.visibility = View.VISIBLE
                } else {

                    addToBasket.visibility = View.VISIBLE
                    removeFromBasket.visibility = View.GONE
                }
            })
    }
}