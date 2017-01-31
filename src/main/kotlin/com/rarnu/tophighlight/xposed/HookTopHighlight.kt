package com.rarnu.tophighlight.xposed

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.view.ViewGroup
import com.rarnu.tophighlight.Consts
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers

/**
 * Created by rarnu on 1/11/17.
 */
object HookTopHighlight {

    fun hookTopHighlight(classLoader: ClassLoader?, prefs: XSharedPreferences?) {
        XposedHelpers.findAndHookMethod(Versions.conversationAdapter, classLoader, "getView", Integer.TYPE, View::class.java, ViewGroup::class.java, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(pmethod: MethodHookParam) {
                val ad = XposedHelpers.callMethod(pmethod.thisObject, Versions.userInfoMethod, pmethod.args[0])
                val j = XposedHelpers.callMethod(pmethod.thisObject, Versions.topInfoMethod, ad)
                val oLH = XposedHelpers.getBooleanField(j, Versions.topInfoField)
                if (oLH) {
                    (pmethod.result as View).background = createSelectorDrawable(pmethod.args[0] as Int, prefs)
                }
            }
        })
    }

    private fun createSelectorDrawable(idx: Int, prefs: XSharedPreferences?): StateListDrawable? {
        val stateList = StateListDrawable()
        val defaultColor = prefs?.getInt("default_$idx", Consts.COLOR_DEFAULT_ARR[idx])!!
        val highlightColor = prefs?.getInt("highlight_$idx", Consts.COLOR_HIGHLIGHT_ARR[idx])!!
        stateList.addState(intArrayOf(android.R.attr.state_selected), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(android.R.attr.state_focused), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(android.R.attr.state_pressed), ColorDrawable(highlightColor))
        stateList.addState(intArrayOf(), ColorDrawable(defaultColor))
        return stateList
    }

}