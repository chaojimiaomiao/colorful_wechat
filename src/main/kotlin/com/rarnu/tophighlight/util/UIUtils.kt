package com.rarnu.tophighlight.util

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.WindowManager
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder


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

    fun getDarkerColor(color: Int, factor: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] = hsv[2] * factor
        return Color.HSVToColor(hsv)
    }

    fun getReverseColor(color: Int): Int = Color.rgb(255 - Color.red(color), 255 - Color.green(color), 255 - Color.blue(color))

    fun showDialog(context: Context, pickerClickListener: ColorPickerClickListener, longClick: Boolean = false) =
            ColorPickerDialogBuilder
                    .with(context)
                    .setTitle(if (!longClick) "选择背景色" else "选择选中时的颜色")
                    .initialColor(Color.parseColor("#ffffff"))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    // .setOnColorSelectedListener(colorSelectListener)
                    .setPositiveButton("ok", pickerClickListener)
                    .setNegativeButton("cancel", null)
                    .build()
                    .show()

}