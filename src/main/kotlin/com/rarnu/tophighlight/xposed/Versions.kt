package com.rarnu.tophighlight.xposed

/**
 * Created by rarnu on 1/26/17.
 */
object Versions {

    // global
    var inited = false

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
                conversationAdapter = "com.tencent.mm.ui.conversation.b"
                userInfoMethod = "en"
                topInfoMethod = "j"
                topInfoField = "oLH"

                mmFragmentActivity = "com.tencent.mm.ui.MMFragmentActivity"
                chatUIActivity = "com.tencent.mm.ui.chatting.ChattingUI\$a"

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
        }
    }

}