package com.rarnu.tophighlight.xposed

import java.io.Serializable

/**
 * Created by rarnu on 1/26/17.
 */
data class Versions (

    var listviewAct: String?,
    var listviewBackMethod: String?,
    var listviewBackField: String?,

    // top highlight color
    var conversationAdapter: String?,
    var userInfoMethod: String?,
    var topInfoMethod : String?,
    var topInfoField : String?,
    // statusbar immersion
    var mmFragmentActivity : String?,
    var chatUIActivity : String?,
    var getAppCompact : String?,
    var getActionBar : String?,
    var dividerId: Int,
    var actionBarViewId: Int,
    var customizeActionBar : String?,
    var actionBarContainer : String?,

    // tab
    var bottomTabView: String?,
    var bottomMethod : String?,
    var bottomField : String?,
    // tool
    var toolClass : String?,
    var toolMethod : String?,
    var toolField : String?,

    // popup window
    var popupWindowClass : String?,
    var popupField : String?,
    var popupMenuField : String?,
    var popupMenuItemClass : String?,
    var popupMenuItemContainer : String?,
    var popupMenuItemId: Int,
    var popupWindowAdapter : String?,

    // top mac or reader & etc.
    var topMacActivity : String?,
    var topReaderActivity : String?,
    var topMacMethod : String?,
    var topMacField : String?,
    var topReaderMethod : String?,
    var topReaderField : String?,
    var topReaderViewId: Int,
    // settings
    var settingActivity : String?,
    var settingPreference : String?,
    var settingListField : String?,
    var settingAddMethod : String?) : Serializable {

    constructor() : this("","","", "","","","", "", "", "", "", 0,0,"","","","","","","","","","","","","",0,"","","","","","","",0,"","","","")
}