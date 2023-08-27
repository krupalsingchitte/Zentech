package com.kr.zentech.auth

import android.content.Context
import androidx.core.content.edit


object SaveToken {
    private const val PREF_TOKEN = "pref_token"
    private const val PREF_ID = "pref_id"

    fun saveTokenAndId(context: Context, token: String, id: Int) {
        val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(PREF_TOKEN, token)
            putInt(PREF_ID, id)
            apply()
        }
    }

    fun getToken(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(PREF_TOKEN, null)
    }

    fun getId(context: Context): Int {
        val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt(PREF_ID, -1)
    }
}


