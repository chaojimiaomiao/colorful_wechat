package com.rarnu.tophighlight.api

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson



/**
 * Created by rarnu on 2/25/17.
 */
object LocalApi {

    var ctx: Context? = null

    private val KEY_USER_PROFILE_ID = "user_profile_id"
    private val KEY_USER = "user_profile"

    fun clear() {
        val pref = PreferenceManager.getDefaultSharedPreferences(ctx)
        val prefsEditor = pref.edit()
        prefsEditor.putString(KEY_USER, "")
        prefsEditor.putInt(KEY_USER_PROFILE_ID, 0)
        prefsEditor.commit()
    }

    var userId: Int
        get() {
            var r = 0
            if (ctx != null) {
                val pref = PreferenceManager.getDefaultSharedPreferences(ctx)
                r = pref.getInt(KEY_USER_PROFILE_ID, 0)
            }
            return r
        }
        set(value) {
            if (ctx != null) {
                val pref = PreferenceManager.getDefaultSharedPreferences(ctx)
                pref.edit().putInt(KEY_USER_PROFILE_ID, value).apply()
            }
        }

    var curUser : User?
        get() {
            var r :User ?= null
            if (ctx != null) {
                val pref = PreferenceManager.getDefaultSharedPreferences(ctx)
                val gson = Gson()
                val json = pref.getString(KEY_USER, "")
                r = gson.fromJson<User>(json, User::class.java)
                userId = r.id
            }
            return r
        }
        set(value) {
            if (ctx != null) {
                val pref = PreferenceManager.getDefaultSharedPreferences(ctx)
                val prefsEditor = pref.edit()
                val gson = Gson()
                val json = gson.toJson(value)
                prefsEditor.putString(KEY_USER, json)
                prefsEditor.commit()
                userId = value!!.id
            }
        }
}