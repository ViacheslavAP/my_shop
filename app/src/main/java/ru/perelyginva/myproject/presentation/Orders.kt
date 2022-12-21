package ru.perelyginva.myproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.perelyginva.myproject.R
import ru.perelyginva.myproject.databinding.FragmentOrdersBinding


class Orders : Fragment() {

    private var binding: FragmentOrdersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)

         


        return binding?.root
    }


}