package com.rarnu.tophighlight.xposed

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ListView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import java.io.File

/**
 * Created by zhaibingjie on 17/3/20.
 */

object HookListBack {

    private var backDrawable: Drawable? = null

    //bCnz
    fun hookFirstPageBackground(classLoader: ClassLoader?) {
        XposedHelpers.findAndHookMethod(Versions.listviewAct, classLoader, Versions.listviewBackMethod, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                val pqm = XposedHelpers.getObjectField(param?.thisObject, Versions.listviewBackField) as ListView?
                if (XpConfig.ini == null) {
                    if (backDrawable == null) {
                        if (File(XpConfig.listviewPath).exists()) {
                            val backBitmap = BitmapFactory.decodeFile(XpConfig.listviewPath)
                            backDrawable = BitmapDrawable(backBitmap)
                        }
                    }
                } else {
                    if (backDrawable == null) {
                        if (File(XpConfig.ini?.listPath).exists()) {
                            val backBitmap = BitmapFactory.decodeFile(XpConfig.ini?.listPath)
                            backDrawable = BitmapDrawable(backBitmap)
                        }
                    }
                }
                if (backDrawable != null) {
                    pqm?.background = backDrawable
                }
            }
        })
    }
}