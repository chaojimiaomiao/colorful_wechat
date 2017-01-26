package com.rarnu.tophighlight.xposed

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.view.ViewGroup
import com.rarnu.tophighlight.Consts
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * Created by rarnu on 1/11/17.
 */
class XpWechatTopHighlight : IXposedHookLoadPackage {

    private val _supportVersions = arrayOf("6.5.4")
    private var _prefs: XSharedPreferences? = null
    private var _ctx: Context? = null

    @Throws(Throwable::class)
    override fun handleLoadPackage(param: XC_LoadPackage.LoadPackageParam) {
        val pkgName = param.packageName
        if (!(pkgName.contains("com.tencen") && pkgName.contains("mm"))) {
            return
        }
        try {
            val activityThread = XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.ActivityThread", null), "currentActivityThread")
            _ctx = XposedHelpers.callMethod(activityThread, "getSystemContext") as Context?
            try {
                val versionName = _ctx?.packageManager?.getPackageInfo(pkgName, 0)?.versionName
                val idx = _supportVersions.indexOfFirst { versionName!!.contains(it) }
                if (idx != -1)
                    Versions.initVersion(idx)
            } catch (t: Throwable) {

            }
        } catch (t: Throwable) {

        }

        if (Versions.inited) {
            XposedHelpers.findAndHookMethod(Versions.conversationAdapter, param.classLoader, "getView", Integer.TYPE, View::class.java, ViewGroup::class.java, object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(pmethod: MethodHookParam) {
                    val ad = XposedHelpers.callMethod(pmethod.thisObject, Versions.userInfoMethod, pmethod.args[0])
                    val j = XposedHelpers.callMethod(pmethod.thisObject, Versions.topInfoMethod, ad)
                    val oLH = XposedHelpers.getBooleanField(j, Versions.topInfoField)
                    if (oLH) {
                        (pmethod.result as View).background = createSelectorDrawable(pmethod.args[0] as Int)
                    }
                }
            })
        }
    }

    private fun createSelectorDrawable(idx: Int): StateListDrawable? {
        _prefs = XSharedPreferences(Consts.PKGNAME, Consts.PREF)
        _prefs?.makeWorldReadable()
        _prefs?.reload()
        val stateList = StateListDrawable()
        val defaultColor = _prefs?.getInt("default_$idx", Consts.COLOR_DEFAULT_ARR[idx])!!
        val highlightColor = _prefs?.getInt("highlight_$idx", Consts.COLOR_HIGHLIGHT_ARR[idx])!!
        stateList.addState(intArrayOf(android.R.attr.state_selected), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(android.R.attr.state_focused), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(android.R.attr.state_pressed), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(), ColorDrawable(defaultColor))
        return stateList
    }

}