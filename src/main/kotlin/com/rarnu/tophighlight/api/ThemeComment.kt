package com.rarnu.tophighlight.api

import org.json.JSONObject
import java.io.Serializable

/**
 * Created by rarnu on 2/14/17.
 */
data class ThemeComment(
        var id: Int,
        var author: Int,
        var nickname: String?,
        var publishdate: String?,
        var comment: String?) : Serializable {

    companion object {
        fun fromJson(jsonStr: String?): ThemeComment? = try {
            fromJson(JSONObject(jsonStr))
        } catch (e: Exception) {
            null
        }

        fun fromJson(jsonObj: JSONObject?): ThemeComment? {
            var ret: ThemeComment? = null
            try {
                if (jsonObj != null) {
                    with(jsonObj) {
                        ret = ThemeComment(getInt("id"), getInt("author"), getString("nickname"), getString("publishdate"), getString("comment"))
                    }
                }
            } catch (e: Exception) {

            }
            return ret
        }
    }
}