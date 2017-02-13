package com.rarnu.tophighlight

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.flask.colorpicker.builder.ColorPickerClickListener
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

        XpConfig.load(this)
        initScrollView()
        refreshStatusBar()
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
        bottomBar?.setOnClickListener { UIUtils.showDialog(this, ColorPickerClickListener { dialogInterface, selectColor, ints ->
            bottomBar?.setBackgroundColor(selectColor)
            XpConfig.bottomBarColor = selectColor
            XpConfig.saveBottomBar(this)
        }) }
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

}
