package ui.smartpro.nasageek

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

private const val PREF_NAME = "preferences_theme"
private const val NIGHT_MODE = "preferences_night_mode"
private const val MARS_MODE = "preferences_mars_mode"
private const val MOON_MODE = "preferences_moon_mode"
private const val KOSMOS_MODE = "preferences_kosmos_mode"
private const val LIGHT_MODE = "preferences_light_mode"
private const val FRAGMENT = "preferences_fragment"

private fun Context.getPreferences(): SharedPreferences = getSharedPreferences(PREF_NAME, 0)

fun Context.switchNightMode() {
    val nightMode = !getNightMode()
    Log.d("Logcat", "nightMode: switched to $nightMode")
    getPreferences().edit().putBoolean(LIGHT_MODE, nightMode).apply()
}

fun Context.switchLightMode() {
    val lightMode = !getNightMode()
    Log.d("Logcat", "nightMode: switched to $lightMode")
    getPreferences().edit().putBoolean(NIGHT_MODE, lightMode).apply()
}

fun Context.switchMarsMode() {
    val marsMode = !getMarsMode()
    Log.d("Logcat", "nightMode: switched to $marsMode")
    getPreferences().edit().putBoolean(MARS_MODE, marsMode).apply()
}

fun Context.switchMoonMode() {
    val moonMode = !getMoonMode()
    Log.d("Logcat", "nightMode: switched to $moonMode")
    getPreferences().edit().putBoolean(MOON_MODE, moonMode).apply()
}

fun Context.switchKosmosMode() {
    val kosmosMode = !getKosmosMode()
    Log.d("Logcat", "nightMode: switched to $kosmosMode")
    getPreferences().edit().putBoolean(KOSMOS_MODE, kosmosMode).apply()
}

fun Context.getMoonMode() = getPreferences().getBoolean(MOON_MODE, false)
fun Context.getMarsMode() = getPreferences().getBoolean(MARS_MODE, false)
fun Context.getNightMode() = getPreferences().getBoolean(NIGHT_MODE, false)
fun Context.getKosmosMode() = getPreferences().getBoolean(KOSMOS_MODE, false)
fun Context.getLightMode() = getPreferences().getBoolean(LIGHT_MODE, false)
