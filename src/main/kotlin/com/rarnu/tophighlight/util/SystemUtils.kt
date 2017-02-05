package com.rarnu.tophighlight.util

import android.app.Activity
import android.os.Build
import android.view.View
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

    fun setMiuiStatusBarDarkMode(activity: Activity?, dark: Boolean) = try {
        val clazz = activity?.window?.javaClass
        val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
        val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
        val darkModeFlag = field.getInt(layoutParams)
        val extraFlagField = clazz?.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE)
        extraFlagField?.invoke(activity?.window, if (dark) darkModeFlag else 0, darkModeFlag)
    } catch (e: Exception) {
    }


    fun setMeizuStatusBarDarkIcon(activity: Activity?, dark: Boolean) = try {
        val lp = activity?.window?.attributes
        val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
        val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
        darkFlag.isAccessible = true
        meizuFlags.isAccessible = true
        val bit = darkFlag.getInt(null)
        var value = meizuFlags.getInt(lp)
        value = if (dark) { value or bit } else { value and bit.inv() }
        meizuFlags.setInt(lp, value)
        activity?.window?.attributes = lp
    } catch (e: Exception) {
    }


    fun setDarkStatusIcon(activity: Activity?, dark: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = activity?.window?.decorView
            if (decorView != null) {
                var vis = decorView.systemUiVisibility
                vis = if (dark) {
                    vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
                decorView.systemUiVisibility = vis
            }
        }
    }

}