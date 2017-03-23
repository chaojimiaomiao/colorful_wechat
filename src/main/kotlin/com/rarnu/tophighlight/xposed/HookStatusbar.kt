package com.rarnu.tophighlight.xposed

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import com.rarnu.tophighlight.util.SystemUtils
import com.rarnu.tophighlight.util.UIUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import java.io.File

/**
 * Created by rarnu on 1/30/17.
 */
object HookStatusbar {

    private var backBitmap: Bitmap? = null
    private var backDrawable: Drawable? = null
    var density = 1.0f

    fun hookStatusbar(classLoader: ClassLoader?, ver: Versions) {

        XposedHelpers.findAndHookMethod(ver.mmFragmentActivity, classLoader, "onResume", object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                val activity = param!!.thisObject as Activity
                val dm = DisplayMetrics()
                activity.windowManager.defaultDisplay.getMetrics(dm)
                density = dm.density
                val activityName = activity.javaClass.name
                if (!VersionStatic.expectImmersionList.contains(activityName)) {
                    val actionbar = XposedHelpers.callMethod(XposedHelpers.callMethod(activity, ver.getAppCompact), ver.getActionBar)
                    if (actionbar != null) {
                        XposedHelpers.callMethod(actionbar, "setBackgroundDrawable", ColorDrawable(XpConfig.statusBarColor))
                        if (XpConfig.darkerStatusBar) {
                            activity.window.statusBarColor = UIUtils.getDarkerColor(XpConfig.statusBarColor, 0.85f)
                        }
                    }
                    if (SystemUtils.isMIUI()) {
                        SystemUtils.setMiuiStatusBarDarkMode(activity, XpConfig.darkStatusBarText)
                    } else if (SystemUtils.isFlyme()) {
                        SystemUtils.setMeizuStatusBarDarkIcon(activity, XpConfig.darkStatusBarText)
                    } else {
                        SystemUtils.setDarkStatusIcon(activity, XpConfig.darkStatusBarText)
                    }
                }
            }

            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                val activity = param!!.thisObject as Activity
                val actionbar = XposedHelpers.callMethod(XposedHelpers.callMethod(activity, ver.getAppCompact), ver.getActionBar)
                if (actionbar != null) {
                    (XposedHelpers.callMethod(actionbar, "getCustomView") as ViewGroup).findViewById(ver.dividerId).visibility = if (XpConfig.showDivider) View.VISIBLE else View.INVISIBLE
                    val divider = (XposedHelpers.callMethod(actionbar, "getCustomView") as ViewGroup).findViewById(ver.dividerId)
                    if (XpConfig.showDivider) {
                        divider.setBackgroundColor(XpConfig.dividerColor)
                        divider.layoutParams.height = 1
                    } else {
                        divider.layoutParams.height = 0
                    }
                    if (XpConfig.darkerStatusBar) {
                        activity.window.statusBarColor = UIUtils.getDarkerColor(XpConfig.statusBarColor, 0.85f)
                    }
                }
            }
        })

        XposedHelpers.findAndHookMethod(ver.chatUIActivity, classLoader, ver.customizeActionBar, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                val actionBarContainer = XposedHelpers.getObjectField(param.thisObject, ver.actionBarContainer) as ViewGroup?
                if (actionBarContainer != null) {
                    val actionbarView = actionBarContainer.findViewById(ver.actionBarViewId) as ViewGroup

                    if (XpConfig.ini != null) {
                        XpConfig.statusBarColor = XpConfig.ini!!.statusBarColor
                        XpConfig.bottomBarPath = XpConfig.ini!!.bottomBarPath
                    }

                    if (File(XpConfig.bottomBarPath).exists() && backDrawable == null) {
                        val tmp = BitmapFactory.decodeFile(XpConfig.bottomBarPath)
                        backDrawable = BitmapDrawable(tmp)
                    }

                    if (backDrawable != null) {
                        actionbarView.background = backDrawable
                    } else {
                        actionbarView.setBackgroundColor(XpConfig.statusBarColor)
                    }

                    val divider = actionbarView.findViewById(ver.dividerId)
                    divider.visibility = if (XpConfig.showDivider) View.VISIBLE else View.INVISIBLE
                    if (XpConfig.showDivider) {
                        divider.setBackgroundColor(XpConfig.dividerColor)
                        divider.layoutParams.height = 1
                    } else {
                        divider.layoutParams.height = 0
                    }
                }
            }
        })

        XposedHelpers.findAndHookMethod(ver.toolClass, classLoader, ver.toolMethod, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val padding = (12 * density + 0.5f).toInt()
                val d = InsetDrawable(ColorDrawable(XpConfig.statusBarColor), padding, padding, padding, padding)
                XposedHelpers.callMethod(XposedHelpers.getObjectField(param.thisObject, ver.toolField), "setBackgroundDrawable", d)
            }
        })

        XposedHelpers.findAndHookMethod("android.content.res.Resources", classLoader, "getColor", Integer.TYPE, object : XC_MethodHook() {
            var needChange = false

            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                needChange = VersionStatic.colorToChange.contains(param.args[0] as Int)
            }

            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                if (needChange) {
                    param.result = if (XpConfig.darkerStatusBar) UIUtils.getDarkerColor(XpConfig.statusBarColor, 0.85f) else XpConfig.statusBarColor
                }
            }
        })
    }
}