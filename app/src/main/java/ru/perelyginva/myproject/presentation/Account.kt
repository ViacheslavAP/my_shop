package ru.perelyginva.myproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.myproject.data.models.CartModel
import ru.perelyginva.myproject.databinding.FragmentAccountBinding
import ru.perelyginva.myproject.presentation.adapter.CartAdapter
import ru.perelyginva.myproject.presentation.adapter.OrderAdapter
import ru.perelyginva.myproject.presentation.viewModel.OrderLocalViewModel


class Account : Fragment() {

    private var binding: FragmentAccountBinding? = null
    private var orderAdapter: OrderAdapter? = null
    private val orderLocalViewModelModel: OrderLocalViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)

        initRecyclerOrder()
        loadOrders()

        return binding?.root
    }

    private fun initRecyclerOrder() {

        binding?.listOrders?.layoutManager = LinearLayoutManager(context)
        orderAdapter = OrderAdapter()
        binding?.listOrders?.adapter = orderAdapter

    }

    private fun loadOrders() {

        orderLocalViewModelModel.loadOrder.observe(viewLifecycleOwner, Observer {
            orderAdapter?.setList(it)
            orderAdapter?.notifyDataSetChanged()

        })
    }
}