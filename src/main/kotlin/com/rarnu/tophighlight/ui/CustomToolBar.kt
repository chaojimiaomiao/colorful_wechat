package com.rarnu.tophighlight.ui

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig

/**
 * Created by zhaibingjie on 17/4/10.
 */

class CustomToolBar : Toolbar {
    constructor(context: Context?) : super(context) {
        refreshStatusBar()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        refreshStatusBar()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        refreshStatusBar()
    }


    private fun refreshStatusBar() {
        val isWhite = UIUtils.isSimilarToWhite(XpConfig.statusBarColor)

        setTitleTextColor(if (isWhite) Color.BLACK else Color.WHITE)
        setTitleTextAppearance(this.context, R.style.MyTitle)
        setBackgroundColor(XpConfig.statusBarColor)

    }
}
