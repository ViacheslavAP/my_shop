package ru.perelyginva.myproject.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.perelyginva.myproject.R
import ru.perelyginva.myproject.databinding.FragmentCheckoutBinding


class Checkout : BottomSheetDialogFragment() {

    private var binding: FragmentCheckoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCheckoutBinding.inflate(layoutInflater, container, false)




        return  binding?.root
    }


}