package ru.perelyginva.myproject.presentation.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import ru.perelyginva.myproject.databinding.FragmentHomeBinding
import ru.perelyginva.myproject.presentation.adapter.ProposalAdapter


class Home : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding?.slider?.adapter = ProposalAdapter(context as FragmentActivity)

        val tabLayoutMediator = binding?.tabSlider?.let {
            binding?.slider?.let { it1 ->
                TabLayoutMediator(it,
                    it1
                ) { tab, position -> }
            }
        }
        tabLayoutMediator?.attach()
        return binding?.root
    }
}