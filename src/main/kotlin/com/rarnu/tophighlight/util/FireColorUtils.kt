package com.rarnu.tophighlight.util

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.Color
import android.graphics.Rect
import android.provider.Settings
import de.robv.android.xposed.XposedHelpers
import java.io.ByteArrayOutputStream


/**
 * Created by rarnu on 1/30/17.
 */
object FireColorUtils {

    var NULL_COLOR = 4573
    var BLOCK_NAV_KEY = "block_nav_"
    var BLOCK_SYS_KEY = "block_sys_"

    fun tintIt(activity: Activity?) {
        try {
            val rect = Rect()
            activity?.window?.decorView?.getWindowVisibleDisplayFrame(rect)
            val top = rect.top
            val decorView = activity?.window?.decorView
            decorView?.isDrawingCacheEnabled = true
            val drawingCache = decorView?.drawingCache
            if (drawingCache != null) {
                val bitmap = Bitmap.createBitmap(drawingCache, 0, top, drawingCache.width, top)
                bitmap.compress(CompressFormat.JPEG, 80, ByteArrayOutputStream())
                XposedHelpers.setAdditionalInstanceField(activity, "tintColor", Integer.valueOf(bitmap.getPixel(bitmap.width / 2, 1)))
                postColor(bitmap.getPixel(bitmap.width / 2, 1), activity)
                return
            }
            throw AssertionError()
        } catch (e: Exception) {
            postColor(NULL_COLOR, activity)
        }
    }

    fun postResult(activity: Activity?) {
        val color = Settings.System.getInt(activity?.contentResolver, activity?.applicationInfo?.packageName + "." + activity?.localClassName, 7354)
        if (color != 7354) {
            postColors(color, activity)
        } else if (activity?.packageName == "android" || activity?.packageName.equals("com.android.systemui", true)) {
            postColor(NULL_COLOR, activity)
        } else if (checkLauncher(activity?.packageName, activity)) {
            postColor(NULL_COLOR, activity)
        } else {
            tintIt(activity)
        }
    }

    fun postColors(color: Int, activity: Activity?) {
        val intent = Intent()
        intent.action = "droidtint"
        intent.putExtra("object", color)
        val nav_block = Settings.System.getInt(activity?.contentResolver, BLOCK_NAV_KEY + activity?.packageName + "." + activity?.localClassName, 0)
        val sys_block = Settings.System.getInt(activity?.contentResolver, BLOCK_SYS_KEY + activity?.packageName + "." + activity?.localClassName, 0)
        if (nav_block == 0) {
            intent.putExtra("block_nav", false)
        } else {
            intent.putExtra("block_nav", true)
        }
        if (sys_block == 0) {
            intent.putExtra("block_sys", false)
        } else {
            intent.putExtra("block_sys", true)
        }
        activity?.sendBroadcast(intent)
    }

    fun postColor(color: Int, activity: Activity?) {
        val nav_block = Settings.System.getInt(activity?.contentResolver, BLOCK_NAV_KEY + activity?.packageName + "." + activity?.localClassName, 0)
        val sys_block = Settings.System.getInt(activity?.contentResolver, BLOCK_SYS_KEY + activity?.packageName + "." + activity?.localClassName, 0)
        val intent1 = Intent()
        intent1.action = "droidtint:changeIcons"
        if (isNearToWhite(color)) {
            intent1.putExtra("should", true)
        } else {
            intent1.putExtra("should", false)
        }
        activity?.sendBroadcast(intent1)
        val intent = Intent("droidtint")
        if (nav_block == 0) {
            intent.putExtra("block_nav", false)
        } else {
            intent.putExtra("block_nav", true)
            intent.putExtra("reset", true)
        }
        if (sys_block == 0) {
            intent.putExtra("block_sys", false)
        } else {
            intent.putExtra("block_sys", true)
            intent.putExtra("reset", true)
        }
        intent.putExtra("object", color)
        activity?.sendBroadcast(intent)
    }

    private fun getLaunchers(activity: Activity?): MutableList<String?> {
        val arr = arrayListOf<String?>()
        val pm = activity?.packageManager
        val i = Intent("android.intent.action.MAIN")
        i.addCategory("android.intent.category.HOME")
        pm?.queryIntentActivities(i, 0)?.mapTo(arr) { it.activityInfo.packageName }
        return arr
    }

    fun checkLauncher(packageName: String?, activity: Activity?): Boolean = getLaunchers(activity).any { packageName.equals(it, true) }

    fun darkenColor(color: Int, value: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] = hsv[2] * value
        return Color.HSVToColor(hsv)
    }

    fun strip(nu: Int): Float {
        var result = ""
        result = if (nu < 10) { result + "." + nu } else { "0.9" }
        return java.lang.Float.parseFloat(result)
    }

    fun isNearToWhite(color: Int): Boolean {
        if (color == 17170445) {
            return true
        }
        val rgb = intArrayOf(Color.red(color), Color.green(color), Color.blue(color))
        val brightness = Math.sqrt((rgb[0] * rgb[0]).toDouble() * 0.241 + (rgb[1] * rgb[1]).toDouble() * 0.691 + (rgb[2] * rgb[2]).toDouble() * 0.068).toInt()
        if (brightness <= 40) {
            return false
        }
        if (brightness < 215) {
            return false
        }
        return true
    }
}