package ui.smartpro.nasageek

import android.os.Bundle
import ui.smartpro.nasageek.settings.ThemeSettings

import ui.smartpro.nasageek.ui.main.MainFragment

class MainActivity : ThemeSettings() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getPreference()

        var bundle = Bundle().getInt(BUNDLE_EXTRA_THEME,0)
        setAppTheme(bundle)

        setTheme(getAppTheme(R.style.AppThemeLight))
//        recreate()
//        setTheme(R.style.AppThemeMoon)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }


    }


    companion object {
        const val BUNDLE_EXTRA_THEME = "theme"
    }
}