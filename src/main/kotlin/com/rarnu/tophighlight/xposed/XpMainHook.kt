package com.rarnu.tophighlight.xposed

import android.content.Context
import com.rarnu.tophighlight.Consts
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

/**
 * Created by rarnu on 1/31/17.
 */
class XpMainHook : IXposedHookLoadPackage {

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
                if (idx != -1) {
                    Versions.initVersion(idx)
                }
            } catch (t: Throwable) {

            }
        } catch (t: Throwable) {

        }

        if (Versions.inited) {
            _prefs = XSharedPreferences(Consts.PKGNAME, Consts.PREF)
            _prefs?.makeWorldReadable()
            _prefs?.reload()
            HookTopHighlight.hookTopHighlight(param.classLoader, _prefs)
            HookStatusbar.hookStatusbar(param.classLoader, _prefs)
        }

    }
}