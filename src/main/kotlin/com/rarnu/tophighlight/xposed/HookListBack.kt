package com.rarnu.tophighlight.xposed

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ListView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import java.io.File

/**
 * Created by zhaibingjie on 17/3/20.
 */

object HookListBack {

    private var backDrawable: Drawable? = null

    fun hookFirstPageBackground(classLoader: ClassLoader?, ver: Versions) {
        //判断版本
        if (VersionStatic.versionName.equals("6.5.7")) {
            Log.w("XposedModule", "HookListBack 6.5.7")
            XposedHelpers.findAndHookConstructor(ver.listviewAct, classLoader, Context::class.java, AttributeSet::class.java, object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {

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
                        (param?.thisObject as View?)?.background = backDrawable
                    }
                }
            })
            return
        }

        XposedHelpers.findAndHookMethod(ver.listviewAct, classLoader, ver.listviewBackMethod, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                val pqm = XposedHelpers.getObjectField(param?.thisObject, ver.listviewBackField) as ListView?
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