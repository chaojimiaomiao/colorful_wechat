package com.rarnu.tophighlight.xposed

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.provider.Settings
import android.view.View
import com.rarnu.tophighlight.util.FireColorUtils
import de.robv.android.xposed.*
import de.robv.android.xposed.XposedHelpers.ClassNotFoundError
import de.robv.android.xposed.callbacks.XC_LoadPackage


/**
 * Created by rarnu on 1/30/17.
 */
class XpImmersion : IXposedHookZygoteInit, IXposedHookLoadPackage {

    private val KK_TRANSPARENT_COLOR_STRING = "#66000000"
    private val KITKAT_TRANSPARENT_COLOR = Color.parseColor(KK_TRANSPARENT_COLOR_STRING)

    var DARKER_TINT_KEY = "darker_tint"
    private var _statusBarView: View? = null
    private var _lastTint = 0

    @Throws(Throwable::class)
    override fun initZygote(param: IXposedHookZygoteInit.StartupParam) {
        _statusBarView = null
    }

    @Throws(Throwable::class)
    override fun handleLoadPackage(param: XC_LoadPackage.LoadPackageParam) {
        if (param.packageName.equals("com.android.systemui", true)) {
            try {
                handleStatusBar(param.classLoader)
                handleNav(param.classLoader)
            } catch (e: Exception) {
                XposedBridge.log(e.cause)
            }
        }
        if (param.packageName.equals("android", true)) {
            try {
                handleActivity(param.classLoader)
            } catch (e2: Exception) {
                XposedBridge.log(e2.cause)
            }
        }
    }

    private fun handleNav(o: ClassLoader) = try {
        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.android.systemui.statusbar.phone.NavigationBarView", o), "setNavigationIconHints", Integer.TYPE, java.lang.Boolean.TYPE, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                val navigationBarView = param.thisObject as View?
                if (navigationBarView != null) {
                    navigationBarView.context.registerReceiver(object : BroadcastReceiver() {
                        override fun onReceive(context: Context, intentz: Intent) {
                            val color = intentz.extras.getInt("object")
                            if (intentz.extras.getBoolean("block_nav")) {
                                setViewBackground(navigationBarView, null)
                                return
                            }
                            if (color == 4573) {
                                setViewBackground(navigationBarView, null)
                            } else {
                                setStatusBarViewBackgrounds(navigationBarView, color)
                            }
                        }
                    }, IntentFilter("droidtint"))
                }
            }
        })
    } catch (e: NoClassDefFoundError) {
    }

    private fun handleActivity(classLoader: ClassLoader?) {
        if (classLoader != null) {
            XposedHelpers.findAndHookMethod(Activity::class.java, "onWindowFocusChanged", java.lang.Boolean.TYPE, object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val activity = param.thisObject as Activity
                    if (param.args[0] as Boolean) {
                        FireColorUtils.postResult(activity)
                    }
                }
            })

            XposedHelpers.findAndHookMethod(Activity::class.java, "finish", object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val activity = param.thisObject as Activity
                    _lastTint = XposedHelpers.getAdditionalInstanceField(activity, "tintColor") as Int
                    FireColorUtils.postColor(FireColorUtils.NULL_COLOR, activity)
                }
            })

            XposedHelpers.findAndHookMethod(Activity::class.java, "onPause", object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val activity = param.thisObject as Activity
                    FireColorUtils.postColor(FireColorUtils.NULL_COLOR, activity)
                    _lastTint = XposedHelpers.getAdditionalInstanceField(activity, "tintColor") as Int
                }
            })
            XposedHelpers.findAndHookMethod(Activity::class.java, "onResume", object : XC_MethodHook() {
                @Throws(Throwable::class)
                override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                    val activity = param.thisObject as Activity
                    FireColorUtils.postResult(activity)
                    _lastTint = XposedHelpers.getAdditionalInstanceField(activity, "tintColor") as Int
                }
            })
        }
    }

    private fun handleStatusBar(classLoader: ClassLoader) {
        var PhoneStatusBarView = try {
            XposedHelpers.findClass("com.android.systemui.statusbar.phone.PhoneStatusBarView", classLoader)
        } catch (e: ClassNotFoundError) {
            XposedHelpers.findClass("com.android.systemui.statusbar.StatusBarView", classLoader)
        }

        XposedBridge.hookAllConstructors(PhoneStatusBarView, object : XC_MethodHook() {
            @Throws(Throwable::class)
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                _statusBarView = param.thisObject as View
                val i = Intent()
                i.action = "updateBatterIcon"
                i.putExtra("key", false)
                _statusBarView?.context?.sendBroadcast(i)
                _statusBarView?.context?.registerReceiver(object : BroadcastReceiver() {
                    override fun onReceive(context: Context, intentz: Intent) {
                        val color = intentz.extras.getInt("object")
                        if (intentz.extras.getBoolean("block_sys")) {
                            setViewBackground(_statusBarView, null)
                            return
                        }
                        var enable_dark = false
                        val nullSys = color == 4573
                        if (Settings.System.getInt(_statusBarView?.context?.contentResolver, DARKER_TINT_KEY, 1) === 1) {
                            enable_dark = true
                        }
                        if (nullSys) {
                            setViewBackground(_statusBarView, null)
                        } else if (enable_dark) {
                            setStatusBarViewBackgrounds(_statusBarView, FireColorUtils.darkenColor(color, FireColorUtils.strip(10)))
                        } else {
                            setStatusBarViewBackgrounds(_statusBarView, color)
                        }
                    }
                }, IntentFilter("droidtint"))
            }
        })
    }

    fun setStatusBarViewBackgrounds(viewBackground: View?, tint_color: Int) {
        val animateTo: Int
        val animateFrom = if (_lastTint === KITKAT_TRANSPARENT_COLOR) 0 else _lastTint
        animateTo = if (tint_color == KITKAT_TRANSPARENT_COLOR) 0 else tint_color
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Integer.valueOf(animateFrom), Integer.valueOf(animateTo))
        colorAnimation.addUpdateListener { animator -> setViewBackground(viewBackground, ColorDrawable((animator.animatedValue as Int).toInt())) }
        colorAnimation.start()
    }

    private fun setViewBackground(view: View?, drawable: Drawable?) {
        view?.background = drawable
    }
}