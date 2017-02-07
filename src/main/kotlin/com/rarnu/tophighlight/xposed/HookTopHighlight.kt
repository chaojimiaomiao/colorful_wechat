package com.rarnu.tophighlight.xposed

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.view.ViewGroup
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers



/**
 * Created by rarnu on 1/11/17.
 */
object HookTopHighlight {

    /**
     * 此处是hook置顶的群
     */
    fun hookTopHighlight(classLoader: ClassLoader?) {
        XposedHelpers.findAndHookMethod(Versions.conversationAdapter, classLoader, "getView", Integer.TYPE, View::class.java, ViewGroup::class.java, object : XC_MethodHook() {
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

    fun hookTopReaderAndMac(classLoader: ClassLoader?) {
        XposedHelpers.findAndHookMethod(Versions.topMacActivity, classLoader, "aii", object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(pmethod: MethodHookParam) {
                val ejD = XposedHelpers.getObjectField(pmethod.thisObject, "ejD") as View
                ejD.setBackgroundColor(XpConfig.macColor)
            }
        })
        XposedHelpers.findAndHookMethod(Versions.topReaderActivity, classLoader, "setVisibility", Integer.TYPE, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(pmethod: MethodHookParam) {
                var view = XposedHelpers.getObjectField(pmethod.thisObject, "view") as View?
                view?.findViewById(0x7f101472)?.setBackgroundColor(XpConfig.readerColor)
            }
        })
    }

    private fun createSelectorDrawable(idx: Int): StateListDrawable? {
        XpConfig.load()
        val stateList = StateListDrawable()
        val defaultColor = XpConfig.topColors[idx]
        val highlightColor = XpConfig.topPressColors[idx]
        stateList.addState(intArrayOf(android.R.attr.state_selected), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(android.R.attr.state_focused), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(android.R.attr.state_pressed), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(), ColorDrawable(defaultColor))
        return stateList
    }

}