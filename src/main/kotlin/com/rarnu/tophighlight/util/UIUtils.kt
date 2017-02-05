package com.rarnu.tophighlight.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.*
import android.widget.GridView
import android.widget.ListView
import android.widget.SearchView
import android.graphics.Color.colorToHSV
import android.graphics.Color



/**
 * Created by rarnu on 3/23/16.
 */
object UIUtils {

    private var context: Context? = null
    var dm: DisplayMetrics? = null

    fun initDisplayMetrics(ctx: Context, wm: WindowManager) {
        context = ctx
        dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
    }

    fun dip2px(dip: Int): Int {
        if (dm == null) {
            return -1
        }
        return (dip * dm!!.density + 0.5f).toInt()
    }

    fun getDarkerColor(color: Int, factor: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] = hsv[2] * factor
        return Color.HSVToColor(hsv)
    }

}