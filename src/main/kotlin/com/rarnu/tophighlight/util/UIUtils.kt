package com.rarnu.tophighlight.util

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.WindowManager
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.rarnu.tophighlight.R


/**
 * Created by rarnu on 3/23/16.
 */
object UIUtils {

    //private var context: Context? = null
    var dm: DisplayMetrics? = null

    fun initDisplayMetrics(ctx: Context, wm: WindowManager) {
        //context = ctx
        dm = DisplayMetrics()
        wm.defaultDisplay.getMetrics(dm)
    }

    fun dip2px(dip: Int): Int {
        if (dm == null) {
            return -1
        }
        return (dip * dm!!.density + 0.5f).toInt()
    }

    fun width(): Int = dm!!.widthPixels
    fun height(): Int = dm!!.heightPixels

    fun getDarkerColor(color: Int, factor: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] = hsv[2] * factor
        return Color.HSVToColor(hsv)
    }

    fun isSimilarToWhite(color: Int): Boolean {
        val gray = (Color.red(color) * 0.2126f + Color.green(color) * 0.7152f + Color.blue(color) * 0.0722f).toInt()
        return gray > 0xcc
    }

    fun showDialog(context: Context, pickerClickListener: ColorPickerClickListener, longClick: Boolean = false) =
            ColorPickerDialogBuilder
                    .with(context)
                    .setTitle(if (!longClick) R.string.view_select_background_color else R.string.view_select_press_color)
                    .initialColor(Color.RED)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setPositiveButton(R.string.alert_ok, pickerClickListener)
                    .setNegativeButton(R.string.alert_cancel, null)
                    .build()
                    .show()

}