package com.rarnu.tophighlight.xposed

/**
 * Created by rarnu on 1/26/17.
 */
object Versions {

    // global
    var inited = false
    var listviewAct = ""
    var listviewBackMethod = ""
    var listviewBackField = ""
    // top highlight color
    var conversationAdapter = ""
    var userInfoMethod = ""
    var topInfoMethod = ""
    var topInfoField = ""
    // statusbar immersion
    var mmFragmentActivity = ""
    var chatUIActivity = ""
    var expectImmersionList = arrayListOf<String>()
    var getAppCompact = ""
    var getActionBar = ""
    var dividerId = 0
    var actionBarViewId = 0
    var customizeActionBar = ""
    var actionBarContainer = ""
    // tab
    var bottomTabView = ""
    var bottomMethod = ""
    var bottomField = ""
    // tool
    var toolClass = ""
    var toolMethod = ""
    var toolField = ""

    // popup window
    var popupWindowClass = ""
    var popupField = ""
    var popupMenuField = ""
    var popupMenuItemClass = ""
    var popupMenuItemContainer = ""
    var popupMenuItemId = 0
    var popupWindowAdapter = ""

    // top mac or reader & etc.
    var topMacActivity = ""
    var topReaderActivity = ""
    var topMacMethod = ""
    var topMacField = ""
    var topReaderMethod = ""
    var topReaderField = ""
    var topReaderViewId = 0
    // settings
    var settingActivity = ""
    var settingPreference = ""
    var settingListField = ""
    var settingAddMethod = ""
    // global resource
    var colorToChange = arrayListOf<Int>()

    fun initVersion(idx: Int) {
        when (idx) {
            0 -> {
                // 654
                listviewAct = "com.tencent.mm.ui.conversation.d"
                listviewBackMethod = "bCn"
                listviewBackField = "pqm"

                conversationAdapter = "com.tencent.mm.ui.conversation.b"
                userInfoMethod = "en"
                topInfoMethod = "j"
                topInfoField = "oLH"

                mmFragmentActivity = "com.tencent.mm.ui.MMFragmentActivity"
                chatUIActivity = "com.tencent.mm.ui.chatting.ChattingUI\$a"

                // bottomTab
                bottomTabView = "com.tencent.mm.ui.LauncherUIBottomTabView"
                bottomMethod = "a"
                bottomField = "ohU"

                // tool
                toolClass = "com.tencent.mm.ui.tools.q"
                toolMethod = "dQ"
                toolField = "oQs"

                // popup window
                popupWindowClass = "com.tencent.mm.ui.base.MMListPopupWindow"
                popupField = "olj"
                popupMenuField = "ole"
                popupMenuItemClass = "com.tencent.mm.ui.t\$d"
                popupMenuItemContainer = "com.tencent.mm.ui.t\$c"
                popupMenuItemId = 0x7f07027d
                popupWindowAdapter = "com.tencent.mm.ui.t\$a"

                // top mac or reader
                topMacActivity = "com.tencent.mm.ui.d.m"
                topReaderActivity = "com.tencent.mm.ui.d.o"
                topMacMethod = "aii"
                topMacField = "ejD"
                topReaderMethod = "setVisibility"
                topReaderField = "view"
                topReaderViewId = 0x7f101472

                // settings
                settingActivity = "com.tencent.mm.plugin.setting.ui.setting.SettingsAboutSystemUI"
                settingPreference = "com.tencent.mm.ui.base.preference.Preference"
                settingListField = "oHs"
                settingAddMethod = "a"

                expectImmersionList = arrayListOf(
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyIndexUI",
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyPrepareUI",
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI",
                        "com.tencent.mm.plugin.collect.ui.CollectMainUI",
                        "com.tencent.mm.plugin.offline.ui.WalletOfflineCoinPurseUI",
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyMyRecordUI")

                // in mmfragmentactivity
                getAppCompact = "cU"
                getActionBar = "cV"
                dividerId = 2131755267
                actionBarViewId = 2131755252

                // in chatui
                customizeActionBar = "bIT"
                actionBarContainer = "oZl"

                colorToChange = arrayListOf(
                        2131231135,
                        2131231148,
                        2131689968,
                        2131689977,
                        2131690035,
                        2131690068,
                        2131690069,
                        2131690072,
                        2131690082)

                inited = true

            }

            1 -> {
                listviewAct = "com.tencent.mm.ui.conversation.d"
                listviewBackMethod = "bEv"
                listviewBackField = "upT"

                conversationAdapter = "com.tencent.mm.ui.conversation.b"
                userInfoMethod = "er"
                topInfoMethod = "j"
                topInfoField = "tKT"

                mmFragmentActivity = "com.tencent.mm.ui.MMFragmentActivity"
                chatUIActivity = "com.tencent.mm.ui.chatting.En_5b8fbb1e\$a"

                bottomTabView = "com.tencent.mm.ui.LauncherUIBottomTabView"
                bottomMethod = "a"
                bottomField = "thq"

                toolClass = "com.tencent.mm.ui.tools.q"
                toolMethod = "dN"
                toolField = "tPF"

                // popup window
                popupWindowClass = "com.tencent.mm.ui.base.MMListPopupWindow"
                popupMenuItemClass = "com.tencent.mm.ui.t\$d"
                popupMenuItemContainer = "com.tencent.mm.ui.t\$c"
                popupWindowAdapter = "com.tencent.mm.ui.t\$a"

                popupField = "tkN"
                popupMenuField = "tkI"
                popupMenuItemId = 0x7f070287

                topMacActivity = "com.tencent.mm.ui.d.m"
                topReaderActivity = "com.tencent.mm.ui.d.o"
                topMacMethod = "aje"
                topMacField = "jcf"
                topReaderMethod = "setVisibility"
                topReaderField = "view"
                topReaderViewId = 0x7f1014ee

                settingActivity = "com.tencent.mm.plugin.setting.ui.setting.SettingsAboutSystemUI"
                settingPreference = "com.tencent.mm.ui.base.preference.Preference"
                settingListField = "tGW"
                settingAddMethod = "a"

                expectImmersionList = arrayListOf(
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyIndexUI",
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyPrepareUI",
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI",
                        "com.tencent.mm.plugin.collect.ui.CollectMainUI",
                        "com.tencent.mm.plugin.offline.ui.WalletOfflineCoinPurseUI",
                        "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyMyRecordUI")

                // in mmfragmentactivity
                getAppCompact = "cR"
                getActionBar = "cS"
                dividerId = 2131755268
                actionBarViewId = 2131755253

                // in chatui
                customizeActionBar = "bLi"
                actionBarContainer = "tYW"

                colorToChange = arrayListOf(
                        2131231135,
                        2131231148,
                        2131689968,
                        2131689977,
                        2131690035,
                        2131690068,
                        2131690069,
                        2131690072,
                        2131690082)

                inited = true
            }
        }
    }

}