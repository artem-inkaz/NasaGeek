package ui.smartpro.nasageek

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val INIT_FLAG = "INIT_FLAG"
    private const val INIT_THEME = "INIT_THEME"
    private const val NAME_PREF = "PREFERENCES"

    private lateinit var mPreferences: SharedPreferences

    // получаем настройки из контекста
    fun getPreference(context: Context): SharedPreferences {
        mPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return mPreferences
    }

    // задаем параметры Adult
    fun setTheme(initTheme: Int) {
        mPreferences.edit()
                .putInt(INIT_THEME, initTheme)
                .apply()
    }

    // получение из наших настроек Adult
    fun getTheme(): Int {
        //в случае если не был установлен флаг Adult
        return mPreferences.getInt(INIT_THEME, 0)
    }
}