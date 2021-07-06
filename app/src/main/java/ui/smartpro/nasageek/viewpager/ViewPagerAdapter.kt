package ui.smartpro.nasageek.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ui.smartpro.nasageek.earth.EarthFragment
import ui.smartpro.nasageek.mars.MarsFragment
import ui.smartpro.nasageek.ui.main.MainFragment

class ViewPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    val fragments = arrayOf(EarthFragment(), MarsFragment(), MainFragment())

    override fun getItemCount(): Int =  fragments.size

    override fun createFragment(position: Int): Fragment =fragments[position]

}
