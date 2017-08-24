package com.rarnu.tophighlight.api

import java.io.Serializable

/**
 * Created by rarnu on 4/20/17.
 */
class ThemeINI: Serializable {

    //新增的
    var themeId = 0
    var themeName = ""
    var type = 0
    var normalColor = 0
    var normalPressColor = 0
    var statusBarPath = ""
    var bottomBarPath = ""
    var listPath = ""

    //原有的
    var statusBarColor = 0
    var showDivider = false
    var dividerColor = 0
    var darkerStatusBar = false
    var darkStatusBarText = false
    var macColor = 0
    var macPressColor = 0
    var readerColor = 0
    var readerPressColor = 0
    var bottomBarColor = 0

    var topColors0 = 0
    var topColors1 = 0
    var topColors2 = 0
    var topColors3 = 0
    var topColors4 = 0
    var topColors5 = 0
    var topColors6 = 0
    var topColors7 = 0
    var topColors8 = 0
    var topColors9 = 0

    var topPressColors0 = 0
    var topPressColors1 = 0
    var topPressColors2 = 0
    var topPressColors3 = 0
    var topPressColors4 = 0
    var topPressColors5 = 0
    var topPressColors6 = 0
    var topPressColors7 = 0
    var topPressColors8 = 0
    var topPressColors9 = 0

    constructor()

    constructor(
            themeId: Int,
            themeName: String,
            type: Int,
            normalColor: Int,
            normalPressColor: Int,
            statusBarPath: String,
            bottomBarPath: String,
            listPath: String,

            statusBarColor: Int,
            showDivider: Boolean,
            dividerColor: Int,
            darkerStatusBar: Boolean,
            darkStatusBarText: Boolean,
            macColor: Int,
            macPressColor: Int,
            readerColor: Int,
            readerPressColor: Int,
            bottomBarColor: Int,
            topColors0: Int,
            topColors1: Int,
            topColors2: Int,
            topColors3: Int,
            topColors4: Int,
            topColors5: Int,
            topColors6: Int,
            topColors7: Int,
            topColors8: Int,
            topColors9: Int,
            topPressColors0: Int,
            topPressColors1: Int,
            topPressColors2: Int,
            topPressColors3: Int,
            topPressColors4: Int,
            topPressColors5: Int,
            topPressColors6: Int,
            topPressColors7: Int,
            topPressColors8: Int,
            topPressColors9: Int) {

        this.themeId = themeId
        this.themeName = themeName
        this.type = type
        this.normalColor = normalColor
        this.normalPressColor = normalPressColor
        this.statusBarPath = statusBarPath
        this.bottomBarPath = bottomBarPath
        this.listPath = listPath

        this.statusBarColor = statusBarColor
        this.showDivider =showDivider
        this.dividerColor = dividerColor
        this.darkerStatusBar = darkerStatusBar
        this.darkStatusBarText = darkStatusBarText
        this.macColor = macColor
        this.macPressColor = macPressColor
        this.readerColor = readerColor
        this.readerPressColor = readerPressColor
        this.bottomBarColor = bottomBarColor
        this.topColors0 = topColors0
        this.topColors1 = topColors1
        this.topColors2 = topColors2
        this.topColors3 = topColors3
        this.topColors4 = topColors4
        this.topColors5 = topColors5
        this.topColors6 = topColors6
        this.topColors7 = topColors7
        this.topColors8 = topColors8
        this.topColors9 = topColors9
        this.topPressColors0 = topPressColors0
        this.topPressColors1 = topPressColors1
        this.topPressColors2 = topPressColors2
        this.topPressColors3 = topPressColors3
        this.topPressColors4 = topPressColors4
        this.topPressColors5 = topPressColors5
        this.topPressColors6 = topPressColors6
        this.topPressColors7 = topPressColors7
        this.topPressColors8 = topPressColors8
        this.topPressColors9 = topPressColors9

    }

    override fun toString(): String {
        return ""
    }

}