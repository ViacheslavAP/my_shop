package ru.perelyginva.myproject.presentation.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.perelyginva.myproject.databinding.FragmentOrdersBinding


class Orders : Fragment() {

    private var _binding: FragmentOrdersBinding? = null
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

}