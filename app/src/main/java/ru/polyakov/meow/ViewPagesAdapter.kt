package ru.polyakov.meow

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.polyakov.meow.fragments.HistoryFragment
import ru.polyakov.meow.fragments.LikesFragment
import ru.polyakov.meow.fragments.MainFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> HistoryFragment()
            2 -> LikesFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}