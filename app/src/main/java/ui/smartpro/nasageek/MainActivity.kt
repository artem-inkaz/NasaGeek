package ui.smartpro.nasageek

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.view_pager.*
import ui.smartpro.nasageek.databinding.MainActivityBinding
import ui.smartpro.nasageek.settings.SettingFragment
import ui.smartpro.nasageek.ui.main.BottomNavigationDrawerFragment
import ui.smartpro.nasageek.ui.main.MainFragment
import ui.smartpro.nasageek.viewpager.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    var bundle: Int? = null

    private var _binding: MainActivityBinding? = null
    // сссылка ссылается на наш _binding данная строчка val mBinding get() = _binding!! позволит избежать проверки на null
    // _binding!! - будет 100% не null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.getPreference(this)
        bundle = AppPreferences.getTheme()
        Log.d("TT", "Наша тема ${bundle}")
        bundle?.let { onThemeActivity(it) }
//        Bundle().putInt(SettingFragment.BUNDLE_EXTRA_SETING, bundle!!)
        Bundle().also {
            it.putInt(SettingFragment.BUNDLE_EXTRA_SETING, bundle!!)
//            fragment.arguments = bundleOf("token" to "s1231")
        }
        val fragment = SettingFragment()
        fragment.arguments= bundleOf(SettingFragment.BUNDLE_EXTRA_SETING to bundle)
        // инициализация нашей связки
        _binding = MainActivityBinding.inflate(layoutInflater)
        // root овый макет
        setContentView(mBinding.root)
        setBottomSheetBehavior(findViewById(R.id.bottom_sheet_container))
        setBottomAppBar()
        viewPagerInit()
    }


    private fun viewPagerInit() {
        val vpAdapter = ViewPagerAdapter(this)
       viewPager.adapter = vpAdapter
//        mBinding.viewPager.setCurrentItem(0, true)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = vpAdapter.fragments[position].javaClass.name
        }.attach()
        setHighlightedTab(MAIN)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
//                vpAdapter.getItemId(tab.position)
                setHighlightedTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "START")
        bundle = AppPreferences.getTheme()
        Bundle().putInt(SettingFragment.BUNDLE_EXTRA_SETING, bundle!!)
        Log.d("BUNDLE", "Наша тема ${bundle}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "RESTART")
    }

    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> toast("Favourite")
            R.id.app_bar_settings -> supportFragmentManager.beginTransaction()
                .replace(R.id.container, SettingFragment()).addToBackStack(null)?.commit()
            R.id.app_bar_theme->viewPager.setCurrentItem(0, true)
            android.R.id.home ->
            {
                this.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun onThemeActivity(params: Int) {

        val (theme, icon) =
                when (params) {
                    0, null -> if (getLightMode()) {
                        Pair(R.style.AppThemeLight, R.drawable.ic_dark_sun)
                    } else {
                        Pair(R.style.Theme_NasaGeek, R.drawable.ic_dark_luna)
                    }
                    1 -> if (getNightMode()) {
                        Pair(R.style.AppThemeDark, R.drawable.ic_dark_sun)
                    } else {
                        Pair(R.style.Theme_NasaGeek, R.drawable.ic_dark_luna)
                    }
                    2 -> if (getMarsMode()) {
                        Pair(R.style.AppThemeMars, R.drawable.ic_dark_sun)
                    } else {
                        Pair(R.style.Theme_NasaGeek, R.drawable.ic_dark_luna)
                    }
                    3 -> if (getMoonMode()) {
                        Pair(R.style.AppThemeMoon, R.drawable.ic_dark_sun)
                    } else {
                        Pair(R.style.Theme_NasaGeek, R.drawable.ic_dark_luna)
                    }
                    4 -> if (getKosmosMode()) {
                        Pair(R.style.AppThemeKosmos, R.drawable.ic_dark_sun)
                    } else {
                        Pair(R.style.Theme_NasaGeek, R.drawable.ic_dark_luna)
                    }

                    else -> Pair(R.style.Theme_NasaGeek, R.drawable.ic_dark_luna)
                }

        setTheme(theme)
    }

    private fun setBottomAppBar() {
        setSupportActionBar(findViewById(R.id.bottom_app_bar))
        fab.setOnClickListener {
            if (MainFragment.isMain) {
                MainFragment.isMain = false
                bottom_app_bar.navigationIcon = null
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_back_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                MainFragment.isMain = true
                bottom_app_bar.navigationIcon =
                    ContextCompat.getDrawable(this, R.drawable.ic_hamburger_menu_bottom_bar)
                bottom_app_bar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_plus_fab))
                bottom_app_bar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun setHighlightedTab(position: Int) {
        val layoutInflater = LayoutInflater.from(this@MainActivity)

       tabLayout.getTabAt(EARTH)?.customView = null
        tabLayout.getTabAt(MARS)?.customView = null
        tabLayout.getTabAt(MAIN)?.customView = null

        when (position) {
            EARTH -> {
                setEarthTabHighlighted(layoutInflater)
                main_backdrop.setImageResource(R.drawable.space)
                main_backdrop.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://www.nasa.gov/feature/goddard/2021/nasa-space-lasers-map-meltwater-lakes-in-antarctica-with-striking-precision")

                    })
                }
            }
            MARS -> {
                setMarsTabHighlighted(layoutInflater)
                main_backdrop.setImageResource(R.drawable.mars)
                main_backdrop.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://www.nasa.gov/feature/ames/curiosity-rover-finds-patches-of-rock-record-on-mars-erased")
                    })
                }
            }
            MAIN -> {
                setPhotoTabHighlighted(layoutInflater)
                main_backdrop.setImageResource(R.drawable.solar)
                main_backdrop.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://www.nasa.gov/feature/goddard/2021/operations-underway-to-restore-payload-computer-on-nasas-hubble-space-telescope")
                    })
                }
            }
            else -> {
                setPhotoTabHighlighted(layoutInflater)
            }
        }
    }

    private fun setEarthTabHighlighted(layoutInflater: LayoutInflater) {
        val earth =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_earth, null)
        earth.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorAccent
                )
            )
        tabLayout.getTabAt(EARTH)?.customView = earth
        tabLayout.getTabAt(MARS)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_mars, null)
        tabLayout.getTabAt(MAIN)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_main, null)
    }

    private fun setMarsTabHighlighted(layoutInflater: LayoutInflater) {
        val mars =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_mars, null)
        mars.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorAccent
                )
            )
        tabLayout.getTabAt(EARTH)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_earth, null)
        tabLayout.getTabAt(MARS)?.customView = mars
        tabLayout.getTabAt(MAIN)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_main, null)
    }

    private fun setPhotoTabHighlighted(layoutInflater: LayoutInflater) {
        val photoOfDay =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_main, null)
        photoOfDay.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    R.color.colorAccent
                )
            )
        tabLayout.getTabAt(EARTH)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_earth, null)
        tabLayout.getTabAt(MARS)?.customView =
            layoutInflater.inflate(R.layout.activity_api_custom_tab_mars, null)
        tabLayout.getTabAt(MAIN)?.customView = photoOfDay
    }

    private fun toast(string: String?) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}