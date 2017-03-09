package com.rarnu.tophighlight

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bingjie.colorpicker.builder.ColorPickerClickListener
import com.getbase.floatingactionbutton.FloatingActionButton
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.market.ThemeListActivity
import com.rarnu.tophighlight.util.SystemUtils
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig


class MainActivity : Activity(), View.OnClickListener {


    private var layMain: LinearLayout? = null

    private var toolBar: Toolbar? = null
    private var chkDarkStatusBar: CheckBox? = null
    private var chkDarkStatusBarText: CheckBox? = null
    private var tvTitle: TextView? = null
    private var bottomBar: ImageView? = null
    // private var scrollView: NestedScrollView? = null

    private var fabTheme: FloatingActionButton? = null
    private var fabFeedback: FloatingActionButton? = null
    private var fabAbout: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.initDisplayMetrics(this, windowManager)
        WthApi.load()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage) //main

        layMain = findViewById(R.id.layMain) as LinearLayout?
        toolBar = findViewById(R.id.first_toolbar) as Toolbar?
        toolBar?.setBackgroundColor(XpConfig.statusBarColor)
        toolBar?.setOnClickListener {
            UIUtils.showDialog(this, ColorPickerClickListener {selectColor, integers ->
                it.setBackgroundColor(selectColor)
                XpConfig.statusBarColor = selectColor
                XpConfig.save(this@MainActivity)
                refreshStatusBar()
            })
        }
        chkDarkStatusBar = findViewById(R.id.chkDarkStatusBar) as CheckBox?
        chkDarkStatusBarText = findViewById(R.id.chkDarkStatusBarText) as CheckBox?
        chkDarkStatusBar?.setOnClickListener(this)
        chkDarkStatusBarText?.setOnClickListener(this)
        tvTitle = findViewById(R.id.tvTitle) as TextView?
        bottomBar = findViewById(R.id.first_bottom_bar) as ImageView?
        fabTheme = findViewById(R.id.fabThemes) as FloatingActionButton?
        fabFeedback = findViewById(R.id.fabFeedback) as FloatingActionButton?
        fabAbout = findViewById(R.id.fabAbout) as FloatingActionButton?

        fabTheme?.setOnClickListener(this)
        fabFeedback?.setOnClickListener(this)
        fabAbout?.setOnClickListener(this)

        XpConfig.load(this)
        initScrollView()
        refreshStatusBar()

        sampleINI()
    }

    private fun initScrollView() {
        toolBar?.setBackgroundColor(XpConfig.statusBarColor)
        chkDarkStatusBar?.isChecked = XpConfig.darkerStatusBar
        chkDarkStatusBarText?.isChecked = XpConfig.darkStatusBarText
        //替换掉R.id.bvj的背景色
        initColumnView(R.drawable.mac, R.string.view_mac_login, XpConfig.KEY_MAC_COLOR)
        //替换掉R.id.d3o的背景色
        initColumnView(R.drawable.reader, R.string.view_top_reader, XpConfig.KEY_TOP_READER_COLOR)
        initDingGroup()
        initBottomBar()
    }

    private fun initBottomBar() {
        bottomBar?.setBackgroundColor(XpConfig.bottomBarColor)
        bottomBar?.setOnClickListener {
            UIUtils.showDialog(this, ColorPickerClickListener { selectColor, ints ->
                bottomBar?.setBackgroundColor(selectColor)
                XpConfig.bottomBarColor = selectColor
                XpConfig.saveBottomBar(this)
            })
        }
    }

    private fun initColumnView(icon: Int, title: Int, key: String) {
        val columnView = ColumnView(this, icon, getString(title), key)
        columnView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layMain?.addView(columnView)
    }

    private fun initDingGroup() {
        (0..3).forEach {
            val colorItem = GroupColumn(this, R.drawable.group_avatar, "置顶栏目  $it", "${XpConfig.KEY_DING}$it")
            colorItem.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layMain?.addView(colorItem)
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.chkDarkStatusBar -> XpConfig.darkerStatusBar = chkDarkStatusBar!!.isChecked
            R.id.chkDarkStatusBarText -> XpConfig.darkStatusBarText = chkDarkStatusBarText!!.isChecked
            R.id.fabThemes -> {
                startActivity(Intent(this, ThemeListActivity::class.java))
            }
            R.id.fabFeedback -> {
            }
            R.id.fabAbout -> {
            }
        }
        XpConfig.save(this)
        refreshStatusBar()
    }

    private fun refreshStatusBar() {
        val isWhite = UIUtils.isSimilarToWhite(XpConfig.statusBarColor)
        tvTitle?.setTextColor(if (isWhite) Color.BLACK else Color.WHITE)
        chkDarkStatusBar?.setTextColor(if (isWhite) Color.BLACK else Color.WHITE)
        chkDarkStatusBarText?.setTextColor(if (isWhite) Color.BLACK else Color.WHITE)
        window.statusBarColor = if (XpConfig.darkerStatusBar) UIUtils.getDarkerColor(XpConfig.statusBarColor, 0.85f) else XpConfig.statusBarColor
        if (SystemUtils.isMIUI()) {
            SystemUtils.setMiuiStatusBarDarkMode(this, XpConfig.darkStatusBarText)
        } else if (SystemUtils.isFlyme()) {
            SystemUtils.setMeizuStatusBarDarkIcon(this, XpConfig.darkStatusBarText)
        } else {
            SystemUtils.setDarkStatusIcon(this, XpConfig.darkStatusBarText)
        }
    }

    private fun sampleINI() {
        val ini = WthApi.ThemeINI(XpConfig.statusBarColor, XpConfig.showDivider, XpConfig.dividerColor, XpConfig.darkerStatusBar, XpConfig.darkStatusBarText,
                XpConfig.macColor, XpConfig.macPressColor, XpConfig.readerColor, XpConfig.readerPressColor, XpConfig.bottomBarColor,
                XpConfig.topColors[0], XpConfig.topColors[1], XpConfig.topColors[2], XpConfig.topColors[3], XpConfig.topColors[4],
                XpConfig.topColors[5], XpConfig.topColors[6], XpConfig.topColors[7], XpConfig.topColors[8], XpConfig.topColors[9],
                XpConfig.topPressColors[0], XpConfig.topPressColors[1], XpConfig.topPressColors[2], XpConfig.topPressColors[3], XpConfig.topPressColors[4],
                XpConfig.topPressColors[5], XpConfig.topPressColors[6], XpConfig.topPressColors[7], XpConfig.topPressColors[8], XpConfig.topPressColors[9])
        val b = WthApi.writeThemeToINI("/sdcard/theme.ini", ini)
        Log.e("ThemeINI", "save ini => $b")

    }
}
