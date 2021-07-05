package ui.smartpro.nasageek

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ui.smartpro.nasageek.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    var bundle: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppPreferences.getPreference(this)
        bundle = AppPreferences.getTheme()
        Log.d("TT", "Наша тема ${bundle}")
        bundle?.let { onThemeActivity(it) }

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
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

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "START")
        bundle = AppPreferences.getTheme()
        Log.d("BUNDLE", "Наша тема ${bundle}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "RESTART")
    }
}