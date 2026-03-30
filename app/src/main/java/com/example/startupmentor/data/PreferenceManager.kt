package com.example.startupmentor.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("startup_mentor_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val USER_NAME = "user_name"
        private const val USER_EMAIL = "user_email"
        private const val STARTUP_STAGE = "startup_stage"
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(IS_LOGGED_IN, false)

    fun setUserData(name: String, email: String, stage: String) {
        prefs.edit().apply {
            putString(USER_NAME, name)
            putString(USER_EMAIL, email)
            putString(STARTUP_STAGE, stage)
            apply()
        }
    }

    fun getUserName(): String = prefs.getString(USER_NAME, "") ?: ""
    fun getUserEmail(): String = prefs.getString(USER_EMAIL, "") ?: ""
    fun getStartupStage(): String = prefs.getString(STARTUP_STAGE, "") ?: ""

    fun logout() {
        prefs.edit().clear().apply()
    }
}