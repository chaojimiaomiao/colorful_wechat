package com.rarnu.tophighlight.api

import android.content.Context
import android.preference.PreferenceManager

/**
 * Created by rarnu on 2/25/17.
 */
object LocalApi {

    var ctx: Context? = null

    private val KEY_USER_PROFILE_ID = "user_profile_id"

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


}