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
     * 此处是hook置顶的群和普通的群
     */
    fun hookTopHighlight(classLoader: ClassLoader?, ver: Versions) {
        XposedHelpers.findAndHookMethod(ver.conversationAdapter, classLoader, "getView", Integer.TYPE, View::class.java, ViewGroup::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(pmethod: MethodHookParam) {
                val ad = XposedHelpers.callMethod(pmethod.thisObject, ver.userInfoMethod, pmethod.args[0])
                val j = XposedHelpers.callMethod(pmethod.thisObject, ver.topInfoMethod, ad)
                val oLH = XposedHelpers.getBooleanField(j, ver.topInfoField)
                (pmethod.result as View?)?.background = if (oLH) createTopHighlightSelectorDrawable(pmethod.args[0] as Int) else createNormalSelectorDrawable()
            }
        })
    }

    fun hookTopReaderAndMac(classLoader: ClassLoader?, ver: Versions) {
        XposedHelpers.findAndHookMethod(ver.topMacActivity, classLoader, ver.topMacMethod, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(pmethod: MethodHookParam) {
                val v = XposedHelpers.getObjectField(pmethod.thisObject, ver.topMacField) as View?
                v?.background = createTopReaderAndMacSelectorDrawable()
            }
        })
        XposedHelpers.findAndHookMethod(ver.topReaderActivity, classLoader, ver.topReaderMethod, Integer.TYPE, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(pmethod: MethodHookParam) {
                val view = XposedHelpers.getObjectField(pmethod.thisObject, ver.topReaderField) as View?
                view?.findViewById(ver.topReaderViewId)?.background = createTopReaderAndMacSelectorDrawable(1)
            }
        })
    }

    private fun createTopHighlightSelectorDrawable(idx: Int): StateListDrawable? {
        return createSelectorDrawable(XpConfig.topColors[idx], XpConfig.topPressColors[idx])
    }

    private fun createNormalSelectorDrawable(): StateListDrawable? {
        return createSelectorDrawable(XpConfig.normalColor, XpConfig.normalPressColor)
    }

    private fun createTopReaderAndMacSelectorDrawable(type: Int = 0): StateListDrawable? {
        return when (type) {
            0 -> createSelectorDrawable(XpConfig.macColor, XpConfig.macPressColor)
            1 -> createSelectorDrawable(XpConfig.readerColor, XpConfig.readerPressColor)
            else -> null
        }
    }

    private fun createSelectorDrawable(defaultColor: Int, pressColor: Int): StateListDrawable? {
        val stateList = StateListDrawable()
        stateList.addState(intArrayOf(android.R.attr.state_selected), ColorDrawable(pressColor))
        stateList.addState(intArrayOf(android.R.attr.state_focused), ColorDrawable(pressColor))
        stateList.addState(intArrayOf(android.R.attr.state_pressed), ColorDrawable(pressColor))
        stateList.addState(intArrayOf(), ColorDrawable(defaultColor))
        return stateList
    }
}