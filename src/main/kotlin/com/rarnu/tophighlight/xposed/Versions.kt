package com.rarnu.tophighlight.xposed

/**
 * Created by rarnu on 1/26/17.
 */
object Versions {

    var inited = false
    var conversationAdapter = ""
    var userInfoMethod = ""
    var topInfoMethod = ""
    var topInfoField = ""

    fun initVersion(idx: Int) {
        when (idx) {
            0 -> {
                // 654
                conversationAdapter = "com.tencent.mm.ui.conversation.b"
                userInfoMethod = "en"
                topInfoMethod = "j"
                topInfoField = "oLH"
                inited = true
            }
        }
    }

}