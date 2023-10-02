package com.wtt.muslimmedia.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wtt.muslimmedia.fragments.AboutAlQuranFragment
import com.wtt.muslimmedia.fragments.AlJazeeraFragment
import com.wtt.muslimmedia.fragments.CommonFragment
import com.wtt.muslimmedia.fragments.WarningFragment

// SectionPageAdapter inheritance FragmentStateAdapter to override instance of adapter
// this is class will used for set Fragment in viewPager2
class SectionPageAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    // getItemCount set mint of fragment which will be displayed in adapter
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        // set arrange of fragment position from left to right
        return when (position) {
            0 -> CommonFragment()
            1 -> AboutAlQuranFragment()
            2 -> AlJazeeraFragment()
            3 -> WarningFragment()
            else -> CommonFragment()
        }
    }
}