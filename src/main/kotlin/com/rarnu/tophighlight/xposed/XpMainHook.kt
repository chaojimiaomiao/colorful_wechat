package com.rarnu.tophighlight.xposed

import android.content.Context
import android.util.Log
import com.rarnu.tophighlight.api.WthApi
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File

/**
 * Created by rarnu on 1/31/17.
 */
class XpMainHook : IXposedHookLoadPackage {

    @Throws(Throwable::class)
    override fun handleLoadPackage(param: XC_LoadPackage.LoadPackageParam) {
        val pkgName = param.packageName
        if (!pkgName.contains("com.tencent.mm")) {
            return
        }
        if (libPath != "") {
            try {
                Log.e("XposedModule", "jni library path => $libPath")
                WthApi.load(libPath)

            } catch (e: Exception) {
                Log.e("XposedModule", "error: $e")
            }
        }

        var ver: Versions? = null

        try {
            val activityThread = XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.ActivityThread", null), "currentActivityThread")
            val ctx = XposedHelpers.callMethod(activityThread, "getSystemContext") as Context?
            try {
                val versionName = ctx?.packageManager?.getPackageInfo(pkgName, 0)?.versionName
                VersionStatic.versionName = versionName!!
                if (WthApi.checkAndDownloadVersion(versionName)) {
                    ver = WthApi.loadVersion(versionName)
                    Log.e("XposedModule", "ver = $ver")
                } else {
                    ver = WthApi.loadVersion(versionName)
                    Log.e("XposedModule", "local ver = $ver")
                }
            } catch (t: Throwable) {
                Log.e("XposedModule", "loadVersion => $t")
            }
        } catch (t: Throwable) {

        }
        if (ver != null) {
            Log.e("XposedModule", "Version loaded")
            XpConfig.xposedload()
            HookTopHighlight.hookTopReaderAndMac(param.classLoader, ver)
            HookTopHighlight.hookTopHighlight(param.classLoader, ver)
            HookStatusbar.hookStatusbar(param.classLoader, ver)
            HookSettings.hookSettings(param.classLoader, ver)
            HookTabView.hookTabView(param.classLoader, ver)
            HookListBack.hookFirstPageBackground(param.classLoader, ver)
        }
    }

    val libPath: String
        get() {
            var fullPath = ""
            val pkgPath = "/data/app/com.rarnu.tophighlight"
            val soPath = "/lib/arm/libwthapi.so"
            for (i in 1..2) {
                fullPath = "$pkgPath-$i$soPath"
                if (File(fullPath).exists()) {
                    break
                }
            }
            return fullPath
        }
}