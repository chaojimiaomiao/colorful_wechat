package com.rarnu.tophighlight.market

import com.rarnu.tophighlight.api.WthApi

/**
 * Created by zhaibingjie on 17/3/19.
 */
object LocalTheme {
    val THEME_TYPE_FULL = 1

    /*var themePurple = WthApi.ThemeINI(
            2,
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
    )*/

    var themeFlower = WthApi.ThemeINI(
    //新增的
            1, "十里桃林", THEME_TYPE_FULL,
            0x66ffffff, 0xffF5F5F5.toInt(),
            "",
            "",
            "/data/data/colorful/baiyinghua.png",

            0xffffc7c8.toInt(),//var statusBarColo
            false, //var showDivider: Boolean,
            0xffF5F5F5.toInt(),//var dividerColor: Int,
            true, //var darkerStatusBar: Boolean,
            true, //var darkStatusBarText: Boolean,
            0xffF5F5F5.toInt(),//var macColor: Int,
            0xffF5F5F5.toInt(),//var macPressColor: Int,
            0xffF5F5F5.toInt(),//var readerColor: Int,
            0xffF5F5F5.toInt(),//var readerPressColor: Int,
            0xffffffff.toInt(),//var bottomBarColor: Int,
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
            0xffF5F5F5.toInt(),//topPressColors4: Int,
            0xffF5F5F5.toInt(),//topPressColors5: Int,
            0xffF5F5F5.toInt(),//topPressColors6: Int,
            0xffF5F5F5.toInt(),//var topPressColors7: Int,
            0xffF5F5F5.toInt(),//var topPressColors8: Int,
            0xffF5F5F5.toInt()//var topPressColors9: Int
    )

    var themeCar = WthApi.ThemeINI(
            //新增的
            2, "兰博基尼", THEME_TYPE_FULL,
            0x66ffffff, 0xffF5F5F5.toInt(),
            "",
            "",
            "/data/data/colorful/lanbojini.png",

            0xff6495ED.toInt(),//var statusBarColo
            false, //var showDivider: Boolean,
            0xffF5F5F5.toInt(),//var dividerColor: Int,
            true, //var darkerStatusBar: Boolean,
            true, //var darkStatusBarText: Boolean,
            0xffF5F5F5.toInt(),//var macColor: Int,
            0xffF5F5F5.toInt(),//var macPressColor: Int,
            0xffF5F5F5.toInt(),//var readerColor: Int,
            0xffF5F5F5.toInt(),//var readerPressColor: Int,
            0xffffffff.toInt(),//var bottomBarColor: Int,
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
            0xffF5F5F5.toInt(),//topPressColors4: Int,
            0xffF5F5F5.toInt(),//topPressColors5: Int,
            0xffF5F5F5.toInt(),//topPressColors6: Int,
            0xffF5F5F5.toInt(),//var topPressColors7: Int,
            0xffF5F5F5.toInt(),//var topPressColors8: Int,
            0xffF5F5F5.toInt()//var topPressColors9: Int
    )
}