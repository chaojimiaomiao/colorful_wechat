package com.rarnu.tophighlight.xposed

import android.content.Context
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * Created by rarnu on 1/31/17.
 */
class XpMainHook : IXposedHookLoadPackage {

    private val _supportVersions = arrayOf("6.5.4")

    @Throws(Throwable::class)
    override fun handleLoadPackage(param: XC_LoadPackage.LoadPackageParam) {

        val pkgName = param.packageName
        if (!pkgName.contains("com.tencent.mm")) {
            return
        }
        try {
            val activityThread = XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.ActivityThread", null), "currentActivityThread")
            val ctx = XposedHelpers.callMethod(activityThread, "getSystemContext") as Context?
            try {
                val versionName = ctx?.packageManager?.getPackageInfo(pkgName, 0)?.versionName
                val idx = _supportVersions.indexOfFirst { versionName!!.contains(it) }
                if (idx != -1) {
                    Versions.initVersion(idx)
                }
            } catch (t: Throwable) {

            }
        } catch (t: Throwable) {

        }
        if (Versions.inited) {

            HookTopHighlight.hookTopReaderAndMac(param.classLoader)
            HookTopHighlight.hookTopHighlight(param.classLoader)
            HookStatusbar.hookStatusbar(param.classLoader)
            HookSettings.hookSettings(param.classLoader)
            HookTabView.hookTabView(param.classLoader)
        }

    }
}