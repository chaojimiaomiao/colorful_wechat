package com.rarnu.tophighlight.xposed

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import com.rarnu.tophighlight.Consts
import com.rarnu.tophighlight.util.SystemUtils
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers

/**
 * Created by rarnu on 1/30/17.
 */
object HookStatusbar {

    fun hookStatusbar(classLoader: ClassLoader?, prefs: XSharedPreferences?) {

        XposedHelpers.findAndHookMethod(Versions.mmFragmentActivity, classLoader, "onResume", object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                val activity = param!!.thisObject as Activity

                val activityName = activity.javaClass.name
                if (!Versions.expectImmersionList.contains(activityName)) {
                    val actionbar = XposedHelpers.callMethod(XposedHelpers.callMethod(activity, Versions.getAppCompact), Versions.getActionBar)
                    if (actionbar != null) {
                        XposedHelpers.callMethod(actionbar, "setBackgroundDrawable", ColorDrawable(Consts.COLOR_DEFAULT_ARR[0]))
                    }
                    if (SystemUtils.isMIUI()) {
                        SystemUtils.setMiuiStatusBarDarkMode(activity, true)
                    } else if (SystemUtils.isFlyme()) {
                        SystemUtils.setMeizuStatusBarDarkIcon(activity, true)
                    } else {
                        SystemUtils.setDarkStatusIcon(activity, true)
                    }
                }
            }

            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                val activity = param!!.thisObject as Activity
                val actionbar = XposedHelpers.callMethod(XposedHelpers.callMethod(activity, Versions.getAppCompact), Versions.getActionBar)
                if (actionbar != null) {
                    (XposedHelpers.callMethod(actionbar, "getCustomView") as ViewGroup).findViewById(Versions.dividerId).visibility = View.INVISIBLE
                }
            }
        })

        XposedHelpers.findAndHookMethod(Versions.chatUIActivity, classLoader, Versions.customizeActionBar, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                val actionBarContainer = XposedHelpers.getObjectField(param.thisObject, Versions.actionBarContainer) as ViewGroup?
                if (actionBarContainer != null) {
                    val actionbarView = actionBarContainer.findViewById(Versions.actionBarViewId) as ViewGroup
                    actionbarView.setBackgroundColor(Consts.COLOR_DEFAULT_ARR[0])
                    actionbarView.findViewById(Versions.dividerId).visibility = View.INVISIBLE
                }
            }
        })

        XposedHelpers.findAndHookMethod("android.content.res.Resources", classLoader, "getColor", Integer.TYPE, object : XC_MethodHook() {
            var needChange = false

            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: XC_MethodHook.MethodHookParam) {
                needChange = Versions.colorToChange.contains(param.args[0] as Int)
            }

            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                if (needChange) {
                    param.result = Consts.COLOR_DEFAULT_ARR[0]
                }
            }
        })
    }
}