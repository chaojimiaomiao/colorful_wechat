package com.rarnu.tophighlight.xposed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

/**
 * Created by rarnu on 1/31/17.
 */
object HookSettings {
    
    fun hookSettings(classLoader: ClassLoader?) {

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.base.MMListPopupWindow", classLoader, "setAdapter", ListAdapter::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun beforeHookedMethod(param: MethodHookParam) {
                val count = XposedHelpers.callMethod(param.args[0], "getCount") as Int
                if (count == 5) {
                    // add menu item
                    val ole = XposedHelpers.getObjectField(XposedHelpers.getObjectField(param.args[0], "olj"), "ole")
                    val d = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.ui.t\$d", classLoader), 99, "多彩微信", "", 0x7f07027d, 0)
                    val c = XposedHelpers.newInstance(XposedHelpers.findClass("com.tencent.mm.ui.t\$c", classLoader), d)
                    XposedHelpers.callMethod(ole, "put", 5, c)
                }
            }
        })

        XposedHelpers.findAndHookMethod("com.tencent.mm.ui.t\$a", classLoader, "getView", Integer.TYPE, View::class.java, ViewGroup::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: MethodHookParam) {
                val v = param.result as View
                val txt = v.findViewById(0x7f1000dd) as TextView?
                val ctx = XposedHelpers.getObjectField(XposedHelpers.getObjectField(param.thisObject, "olj"), "mContext") as Context?
                if (txt?.text.toString() == "多彩微信") {
                    v.setOnClickListener {
                        val inMain = Intent("com.rarnu.tophighlight.MAIN")
                        inMain.addCategory(Intent.CATEGORY_DEFAULT)
                        ctx?.startActivity(inMain)
                    }
                }
            }
        })
    }

}