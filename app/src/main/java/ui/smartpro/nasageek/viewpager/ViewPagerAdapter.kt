package ui.smartpro.nasageek.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import ui.smartpro.nasageek.earth.EarthFragment
import ui.smartpro.nasageek.mars.MarsFragment
import ui.smartpro.nasageek.ui.main.MainFragment

private const val EARTH_FRAGMENT = 0
private const val MARS_FRAGMENT = 1
private const val WEATHER_FRAGMENT = 2

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStateAdapter(FragmentActivity()) {

    private val fragments = arrayOf(EarthFragment(), MarsFragment(), MainFragment())

    override fun getItemCount(): Int =  fragments.size

    override fun createFragment(position: Int): Fragment =fragments[position]

}
