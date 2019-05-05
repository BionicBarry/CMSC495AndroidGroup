package com.example.samplechatapplication.utils

import android.content.Context
import android.preference.PreferenceManager

/**
 * This is a utility class to interact with shared preference data.
 *
 *
 * Created by pamuj
 */
object SharePreferenceData {

    /**
     * This method set boolean value in shared preference.
     *
     * @param context - Current context
     * @param key     - String representation the key
     * @param value   - boolean representing the value
     */
    fun setSharedPrefBoolean(context: Context?, key: String, value: Boolean) {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }


    /**
     * Returns String value as per preference key passed.
     *
     * @param context      - Current context
     * @param key          - String representation the key
     * @param defaultValue - boolean representing the default value
     * @return boolean
     */
    fun getSharedPrefBoolean(context: Context?, key: String, defaultValue: Boolean): Boolean {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        return settings.getBoolean(key, defaultValue)
    }

    /**
     * This method set String value in shared preference.
     *
     * @param context - Current context
     * @param key     - String representation the key
     * @param value   -String representing the default value
     */
    fun setSharedPrefString(context: Context?, key: String, value: String) {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Returns String value as per preference key passed.
     *
     * @param context      - Current context
     * @param key          - String representation the key
     * @param defaultValue -String representing the default value
     * @return String
     */
    fun getSharedPrefString(context: Context?, key: String, defaultValue: String): String? {
        val token = PreferenceManager.getDefaultSharedPreferences(context)
        return token.getString(key, defaultValue)
    }

    fun setBooleanPreference(context: Context, key: String, value: Boolean) {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanPreference(context: Context, key: String, defaultValue: Boolean): Boolean {
        val token = PreferenceManager.getDefaultSharedPreferences(context)
        return token.getBoolean(key, defaultValue)
    }

    fun clearAllPreference(context: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }
}