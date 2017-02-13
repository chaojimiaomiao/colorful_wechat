package com.rarnu.tophighlight.api

import org.json.JSONObject
import java.io.Serializable

/**
 * Created by rarnu on 2/14/17.
 */
data class Theme(
        var id: Int,
        var name: String?,
        var author: Int,
        var publishdate: String?,
        var description: String?,
        var filepath: String?,
        var downloadcount: Int,
        var starcount: Int,
        var status: Int,
        var stared: Boolean) : Serializable {

    companion object {

        fun fromJson(jsonStr: String?): Theme? = try {
            fromJson(JSONObject(jsonStr))
        } catch (e: Exception) {
            null
        }

        fun fromJson(jsonObj: JSONObject?): Theme? {
            var ret: Theme? = null
            try {
                if (jsonObj != null) {
                    with(jsonObj) {
                        ret = Theme(getInt("id"), getString("name"), getInt("author"), getString("publishdate"), getString("description"), getString("filepath"), getInt("downloadcount"), getInt("starcount"), getInt("status"), getInt("stared") == 1)
                    }
                }
            } catch (e: Exception) {
            }
            return ret
        }

    }

}