package com.rarnu.tophighlight.api

import org.json.JSONObject
import java.io.Serializable

/**
 * Created by rarnu on 2/14/17.
 */
data class User(
        var id: Int,
        var account: String?,
        var nickname: String?,
        var email: String?,
        var head: String?,
        var comment: String?) : Serializable {

    companion object {

        fun fromJson(jsonStr: String?): User? = try {
            fromJson(JSONObject(jsonStr))
        } catch (e: Exception) {
            null
        }

        fun fromJson(jsonObj: JSONObject?): User? {
            var ret: User? = null
            try {
                if (jsonObj != null) {
                    with(jsonObj) {
                        ret = User(getInt("id"), getString("account"), getString("nickname"), getString("email"), getString("head"), getString("comment"))
                    }
                }
            } catch (e: Exception) {
            }
            return ret;
        }
    }
}