package com.rarnu.tophighlight.util

import android.app.Activity
import android.os.Build
import android.view.WindowManager

/**
 * Created by rarnu on 1/30/17.
 */
object SystemUtils {

    private val KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"
    private val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private val KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage"

    fun isMIUI(): Boolean = with(BuildProperties.newInstance()) { getProperty(KEY_MIUI_VERSION_CODE, null) != null || getProperty(KEY_MIUI_VERSION_NAME, null) != null || getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null }
    fun isFlyme(): Boolean = try {
        Build::class.java.getMethod("hasSmartBar") != null
    } catch (e: Exception) {
        false
    }

    fun setMiuiStatusBarDarkMode(activity: Activity?, dark: Boolean): Boolean {
        val clazz = activity?.window?.javaClass
        try {
            var darkModeFlag = 0
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            darkModeFlag = field.getInt(layoutParams)
            val extraFlagField = clazz?.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE)
            extraFlagField?.invoke(activity?.window, if (dark) darkModeFlag else 0, darkModeFlag)
            return true
        } catch (e: Exception) {
        }
        return false
    }

    fun setMeizuStatusBarDarkIcon(activity: Activity?, dark: Boolean): Boolean {
        var result = false
        if (activity != null) {
            try {
                val lp = activity.window.attributes
                val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
                darkFlag.isAccessible = true
                meizuFlags.isAccessible = true
                val bit = darkFlag.getInt(null)
                var value = meizuFlags.getInt(lp)
                value = if (dark) { value or bit } else { value and bit.inv() }
                meizuFlags.setInt(lp, value)
                activity.window.attributes = lp
                result = true
            } catch (e: Exception) {
            }
        }
        return result
    }

}