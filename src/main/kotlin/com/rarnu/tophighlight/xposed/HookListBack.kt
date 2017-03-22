package com.rarnu.tophighlight.xposed

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ListView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

/**
 * Created by zhaibingjie on 17/3/20.
 */

object HookListBack {

    //bCnz
    fun hookFirstPageBackground(classLoader: ClassLoader?) {
        XposedHelpers.findAndHookMethod(Versions.listviewAct, classLoader, Versions.listviewBackMethod, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam?) {
                XpConfig.xposedload()
                val pqm = XposedHelpers.getObjectField(param?.thisObject, Versions.listviewBackField) as ListView?
                var backBitmap = BitmapFactory.decodeFile(XpConfig.listviewPath)
                val drawable = BitmapDrawable(backBitmap)
                pqm?.background = drawable

                Log.e("HookListBack", "HookListBack, before ini")
                if (XpConfig.ini != null) {
                    Log.e("HookListBack", "HookListBack, ini not null")
                    var backBitmap = BitmapFactory.decodeFile(XpConfig.ini!!.listPath)

                    Log.e("HookListBack",  if (backBitmap == null) "bitmap null" else "bitmap not null")
                    val drawable = BitmapDrawable(backBitmap)
                    pqm?.background = drawable
                } else {
                    Log.e("HookListBack", "HookListBack, ini null")
                }
            }
        })
    }
}