package com.koushik.myrestapi

import android.content.Context
import android.content.SharedPreferences

class MyRestAPI(mContext: Context) {
    val mContext = mContext
    var prefs: SharedPreferences? = mContext.getSharedPreferences("com.k", 0)
    fun setRefreshToken(id: String) {
        val editor = prefs!!.edit()
        editor.putString("refresh_token", id)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        return prefs!!.getString("refresh_token", "")
    }
}