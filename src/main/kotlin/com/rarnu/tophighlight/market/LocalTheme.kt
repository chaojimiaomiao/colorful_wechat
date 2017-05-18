package com.rarnu.tophighlight.market

import com.rarnu.tophighlight.api.ThemeINI
import com.rarnu.tophighlight.xposed.XpConfig

/**
 * Created by zhaibingjie on 17/3/19.
 */
object LocalTheme {

    /** 带图片路径的主题 **/
    val THEME_TYPE_PIC = 1
    val THEME_TYPE_PART = 2
    val THEME_TYPE_FULL = 3

    var themePurple = ThemeINI(
            "",
            2,
            "紫色渐变",
            THEME_TYPE_PART,
            0xffF5F5F5.toInt(),
            0xffF5F5F5.toInt(),
            "",
            "",
            "",
            0xffF5F5F5.toInt(),//var statusBarColo
            false, //var showDivider: Boolean,
            0xffF5F5F5.toInt(),//var dividerColor: Int,
            true, //var darkerStatusBar: Boolean,
            true, //var darkStatusBarText: Boolean,
            0xffF5F5F5.toInt(),//var macColor: Int,
            0xffF5F5F5.toInt(),//var macPressColor: Int,
            0xffF5F5F5.toInt(),//var readerColor: Int,
            0xffF5F5F5.toInt(),//var readerPressColor: Int,
            0xffF5F5F5.toInt(),//var bottomBarColor: Int,
            0xffF5F5F5.toInt(),//var topColors0: Int,
            0xffF5F5F5.toInt(),//var topColors1: Int,
            0xffF5F5F5.toInt(),//var topColors2: Int,
            0xffF5F5F5.toInt(),//var topColors3: Int,
            0xffF5F5F5.toInt(),//var topColors4: Int,
            0xffF5F5F5.toInt(),//var topColors5: Int,
            0xffF5F5F5.toInt(),//var topColors6: Int,
            0xffF5F5F5.toInt(),//var topColors7: Int,
            0xffF5F5F5.toInt(),//var topColors8: Int,
            0xffF5F5F5.toInt(),//var topColors9: Int,
            0xffF5F5F5.toInt(),//var topPressColors0: Int,
            0xffF5F5F5.toInt(),//var topPressColors1: Int,
            0xffF5F5F5.toInt(),//var topPressColors2: Int,
            0xffF5F5F5.toInt(),//var topPressColors3: Int,
            0xffF5F5F5.toInt(),//var topPressColors4: Int,
            0xffF5F5F5.toInt(),//var topPressColors5: Int,
            0xffF5F5F5.toInt(),//var topPressColors6: Int,
            0xffF5F5F5.toInt(),//var topPressColors7: Int,
            0xffF5F5F5.toInt(),//var topPressColors8: Int,
            0xffF5F5F5.toInt()//var topPressColors9: Int
    )

    var themeFlower = ThemeINI(
            "",
    //新增的
            1,
            "十里桃林",
            THEME_TYPE_PIC,
            0x66ffffff,
            0xffF5F5F5.toInt(),
            "",
            "",
            XpConfig.BASE_FILE_PATH + "/colorful/baiyinghua.png",
            0xffffc7c8.toInt(),     //var statusBarColo
            false,                  //var showDivider: Boolean,
            -1,                     //var dividerColor: Int,
            true,                   //var darkerStatusBar: Boolean,
            true,                   //var darkStatusBarText: Boolean,
            -1,                     //var macColor: Int,
            -1,                     //var macPressColor: Int,
            -1,                     //var readerColor: Int,
            -1,                     //var readerPressColor: Int,
            0xffffffff.toInt(),     //var bottomBarColor: Int,
            0xffffc7c8.toInt(),     //var topColors0: Int,
            0xfffeeac7.toInt(),     //var topColors1: Int,
            0xffffffc9.toInt(),     //var topColors2: Int,
            -1,                     //var topColors3: Int,
            -1,                     //var topColors4: Int,
            -1,                     //var topColors5: Int,
            -1,                     //var topColors6: Int,
            -1,                     //var topColors7: Int,
            -1,                     //var topColors8: Int,
            -1,                     //var topColors9: Int,
            0xffff8f8e.toInt(),     //var topPressColors0: Int,
            0xffffd38f.toInt(),     //var topPressColors1: Int,
            0xffffff8d.toInt(),     //var topPressColors2: Int,
            -1,                     //var topPressColors3: Int,
            -1,                     //topPressColors4: Int,
            -1,                     //topPressColors5: Int,
            -1,                     //topPressColors6: Int,
            -1,                     //var topPressColors7: Int,
            -1,                     //var topPressColors8: Int,
            -1                      //var topPressColors9: Int
    )

    var themeCar = ThemeINI(
            "",
            //新增的
            2, "兰博基尼", THEME_TYPE_PIC,
            0x66ffffff,
            0xffF5F5F5.toInt(),
            "",
            "",
            XpConfig.BASE_FILE_PATH + "/colorful/lanbojini.png",
            0xff6495ED.toInt(),     //var statusBarColo
            false,                  //var showDivider: Boolean,
            0xffF5F5F5.toInt(),     //var dividerColor: Int,
            true,                   //var darkerStatusBar: Boolean,
            true,                   //var darkStatusBarText: Boolean,
            0xffF5F5F5.toInt(),     //var macColor: Int,
            0xffF5F5F5.toInt(),     //var macPressColor: Int,
            0xffF5F5F5.toInt(),     //var readerColor: Int,
            0xffF5F5F5.toInt(),     //var readerPressColor: Int,
            0xffffffff.toInt(),     //var bottomBarColor: Int,
            0xffF5F5F5.toInt(),     //var topColors0: Int,
            0xffF5F5F5.toInt(),     //var topColors1: Int,
            0xffF5F5F5.toInt(),     //var topColors2: Int,
            0xffF5F5F5.toInt(),     //var topColors3: Int,
            0xffF5F5F5.toInt(),     //var topColors4: Int,
            0xffF5F5F5.toInt(),     //var topColors5: Int,
            0xffF5F5F5.toInt(),     //var topColors6: Int,
            0xffF5F5F5.toInt(),     //var topColors7: Int,
            0xffF5F5F5.toInt(),     //var topColors8: Int,
            0xffF5F5F5.toInt(),     //var topColors9: Int,
            0xffF5F5F5.toInt(),     //var topPressColors0: Int,
            0xffF5F5F5.toInt(),     //var topPressColors1: Int,
            0xffF5F5F5.toInt(),     //var topPressColors2: Int,
            0xffF5F5F5.toInt(),     //var topPressColors3: Int,
            0xffF5F5F5.toInt(),     //topPressColors4: Int,
            0xffF5F5F5.toInt(),     //topPressColors5: Int,
            0xffF5F5F5.toInt(),     //topPressColors6: Int,
            0xffF5F5F5.toInt(),     //var topPressColors7: Int,
            0xffF5F5F5.toInt(),     //var topPressColors8: Int,
            0xffF5F5F5.toInt()      //var topPressColors9: Int
    )

    var themeNormal = ThemeINI(
            "",
            2,
            "默认",
            THEME_TYPE_PIC,
            0x66ffffff,
            0xffF5F5F5.toInt(),
            "",
            "",
            "",
            XpConfig.defaultStatusBarColor,//var statusBarColo
            false, //var showDivider: Boolean,
            0xffF5F5F5.toInt(),//var dividerColor: Int,
            true, //var darkerStatusBar: Boolean,
            true, //var darkStatusBarText: Boolean,
            XpConfig.defaultMacColor,//var macColor: Int,
            XpConfig.defaultMacPressColor,//var macPressColor: Int,
            XpConfig.defaultReaderColor,//var readerColor: Int,
            XpConfig.defaultReaderPressColor,//var readerPressColor: Int,
            XpConfig.defaultBottomBarColor,//var bottomBarColor: Int,
            0xffF5F5F5.toInt(),//var topColors0: Int,
            0xffF5F5F5.toInt(),//var topColors1: Int,
            0xffF5F5F5.toInt(),//var topColors2: Int,
            0xffF5F5F5.toInt(),//var topColors3: Int,
            0xffF5F5F5.toInt(),//var topColors4: Int,
            0xffF5F5F5.toInt(),//var topColors5: Int,
            0xffF5F5F5.toInt(),//var topColors6: Int,
            0xffF5F5F5.toInt(),//var topColors7: Int,
            0xffF5F5F5.toInt(),//var topColors8: Int,
            0xffF5F5F5.toInt(),//var topColors9: Int,
            0xffF5F5F5.toInt(),//var topPressColors0: Int,
            0xffF5F5F5.toInt(),//var topPressColors1: Int,
            0xffF5F5F5.toInt(),//var topPressColors2: Int,
            0xffF5F5F5.toInt(),//var topPressColors3: Int,
            0xffF5F5F5.toInt(),//var topPressColors4: Int,
            0xffF5F5F5.toInt(),//var topPressColors5: Int,
            0xffF5F5F5.toInt(),//var topPressColors6: Int,
            0xffF5F5F5.toInt(),//var topPressColors7: Int,
            0xffF5F5F5.toInt(),//var topPressColors8: Int,
            0xffF5F5F5.toInt()//var topPressColors9: Int
    )
}