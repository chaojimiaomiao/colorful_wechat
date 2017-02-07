package com.rarnu.tophighlight

import android.app.Activity
import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig


class MainActivity : Activity() {

    private var layMain: LinearLayout? = null

    private var toolBar: Toolbar? = null
    // private var scrollView: NestedScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.initDisplayMetrics(this, windowManager)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage)//main

        layMain = findViewById(R.id.layMain) as LinearLayout?
        toolBar = findViewById(R.id.first_toolbar) as Toolbar?
        toolBar?.setBackgroundColor(XpConfig.statusBarColor)
        toolBar?.setOnClickListener {
            UIUtils.showDialog(this, ColorPickerClickListener { dialogInterface, selectColor, integers ->
                it.setBackgroundColor(selectColor)
                XpConfig.statusBarColor = selectColor
            })
        }
        XpConfig.load(this)
        initScrollView()
    }

    fun initScrollView() {
        //替换掉R.id.bvj的背景色
        val columnViewMac = ColumnView(this, null, R.drawable.mac, "Mac微信已登录", XpConfig.KEY_MAC_COLOR)
        columnViewMac.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layMain?.addView(columnViewMac)

        //替换掉R.id.d3o的背景色
        val columnViewRead = ColumnView(this, null, R.drawable.reader, "正在浏览 置顶文章", XpConfig.KEY_TOP_READER_COLOR)
        columnViewRead.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layMain?.addView(columnViewRead)
    }

}
