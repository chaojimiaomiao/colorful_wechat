package com.rarnu.tophighlight.xposed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

/**
 * Created by rarnu on 1/31/17.
 */
object HookSettings {

    var _ctx: Context? = null

    fun hookSettings(classLoader: ClassLoader?) {

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.base.preference.Preference", classLoader, "onBindView", View::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val v = param.args[0] as View?
                val title = XposedHelpers.callMethod(param.thisObject, "getTitle") as String?
                if (title == "多彩微信") {
                    v?.setOnClickListener {
                        val inMain = Intent("com.rarnu.tophighlight.MAIN")
                        inMain.addCategory(Intent.CATEGORY_DEFAULT)
                        _ctx?.startActivity(inMain)
                    }
                }
            }
        })

        XposedHelpers.findAndHookMethod("com.tencent.mm.plugin.setting.ui.setting.SettingsAboutSystemUI", classLoader, "onCreate", Bundle::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                _ctx = param.thisObject as Context  // activity
                val clsPreference = XposedHelpers.findClass("com.tencent.mm.ui.base.preference.Preference", classLoader)
                val objPreference = XposedHelpers.newInstance(clsPreference, _ctx)
                XposedHelpers.callMethod(objPreference, "setTitle", "多彩微信")
                val prefList = XposedHelpers.getObjectField(_ctx, "oHs")
                XposedHelpers.callMethod(prefList, "a", objPreference, 0)

            }
        })
    }

}