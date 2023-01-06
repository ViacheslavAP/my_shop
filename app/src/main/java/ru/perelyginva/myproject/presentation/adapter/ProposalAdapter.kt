package ru.perelyginva.myproject.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.perelyginva.myproject.presentation.proposal.ProposalOne
import ru.perelyginva.myproject.presentation.proposal.ProposalTwo


class ProposalAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ProposalOne()
            }
            1 -> {
                ProposalTwo()
            }

            else -> {
                ProposalOne()
            }
        }
    }

}