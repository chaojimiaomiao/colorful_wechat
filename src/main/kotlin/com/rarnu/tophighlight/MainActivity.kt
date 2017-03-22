package com.rarnu.tophighlight

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.Toolbar
import android.text.SpannableString
import android.text.util.Linkify
import android.util.Log
import android.view.View
import android.widget.*
import com.bingjie.colorpicker.builder.ColorPickerClickListener
import com.getbase.floatingactionbutton.FloatingActionButton
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.market.LocalTheme
import com.rarnu.tophighlight.market.ThemeListActivity
import com.rarnu.tophighlight.util.ImageUtil
import com.rarnu.tophighlight.util.SystemUtils
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig
import java.util.*
import kotlin.concurrent.thread


class MainActivity : Activity(), View.OnClickListener {

    private var layMain: LinearLayout? = null

    private var toolBar: Toolbar? = null
    private var chkDarkStatusBar: CheckBox? = null
    private var chkDarkStatusBarText: CheckBox? = null
    private var tvTitle: TextView? = null
    private var bottomBar: ImageView? = null
    private var scrollView: NestedScrollView? = null

    private var fabTheme: FloatingActionButton? = null
    private var fabFeedback: FloatingActionButton? = null
    private var fabAbout: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.initDisplayMetrics(this, windowManager)
        WthApi.load()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage) //main

        scrollView = findViewById(R.id.first_scroll) as NestedScrollView
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

        thread { WthApi.recordDevice() }

        if (!WthApi.xposedInstalled()) {
            var s = SpannableString(getText(R.string.alert_xposed));
            Linkify.addLinks(s, Linkify.WEB_URLS);
            AlertDialog.Builder(this, R.style.whiteDialogNoFrame)//android.R.style.ThemeOverlay
                    .setMessage(s)
                    .setPositiveButton(R.string.alert_ok, {
                        dialogInterface, i ->
                            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coolapk.com/apk/de.robv.android.xposed.installer")))
                    })
                    .show()
        }

        thread {
            //写入
            var bitmap = BitmapFactory.decodeResource(resources, R.drawable.lanbojini)
            if (ImageUtil.saveImagePrivate(bitmap, "lanbojini", this)) {
                LocalTheme.themeCar.addListPath(getExternalFilesDir("").absolutePath + "/colorful/lanbojini.png")
                Log.e("", "themeCar jpg path: " + LocalTheme.themeCar.listPath)
                WthApi.writeThemeToINI(getExternalFilesDir("").absolutePath + "/colorful/lanbojini.ini", LocalTheme.themeCar)
            }

            var bitmapF = BitmapFactory.decodeResource(resources, R.drawable.baiyinghua)
            if (ImageUtil.saveImagePrivate(bitmapF, "baiyinghua", this)) {
                LocalTheme.themeFlower.addListPath(getExternalFilesDir("").absolutePath + "/colorful/baiyinghua.png")
                WthApi.writeThemeToINI(getExternalFilesDir("").absolutePath + "/colorful/baiyinghua.ini", LocalTheme.themeFlower)
                Log.e("", "themeFlower jpg path: " + LocalTheme.themeFlower.listPath)
            }

            runOnUiThread {
                //读取
                if (XpConfig.ini != null) {
                    var listviewBitmap = BitmapFactory.decodeFile(XpConfig.ini!!.listPath)//XpConfig.listviewPath
                    val drawable = BitmapDrawable(listviewBitmap)
                    scrollView?.setBackground(drawable)
                }

            }
        }
    }

    private fun initScrollView() {
        toolBar?.setBackgroundColor(XpConfig.statusBarColor)
        chkDarkStatusBar?.isChecked = XpConfig.darkerStatusBar
        chkDarkStatusBarText?.isChecked = XpConfig.darkStatusBarText
        //替换掉R.id.bvj的背景色
        initColumnView(R.drawable.mac, R.string.view_mac_login, XpConfig.KEY_MAC_COLOR)
        //替换掉R.id.d3o的背景色
        initColumnView(R.drawable.reader, R.string.view_top_reader, XpConfig.KEY_TOP_READER_COLOR)

        initDingGroupEntry()

        initNormalGroup(5)

        initLocalTheme()
        initBottomBar()
    }

    private fun initDingGroupEntry() {
        val linearLayout = View.inflate(this, R.layout.group_item, null)
        val layoutParams = LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2px(66))
        (linearLayout.findViewById(R.id.group_name) as TextView?)?.text = getString(R.string.ding_group_entry)
        (linearLayout.findViewById(R.id.group_image) as ImageView?)?.setImageResource(R.drawable.group_avatar)
        linearLayout.setOnClickListener { startActivity(Intent(this, TopListActivity::class.java)) }
        layMain?.addView(linearLayout, layoutParams)
    }

    private fun initNormalGroup(nums :Int) {
        val colorItem = GroupColumn(this, getString(R.string.normal_group), XpConfig.KEY_NORMAL_COLOR)
        colorItem.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layMain?.addView(colorItem)
    }

    private fun initLocalTheme() {
        //val ini = WthApi.readThemeFromINI("/sdcard/theme.ini")
        var checkboxList: ArrayList<CheckBox> = ArrayList()

        fun clearCheckboxs() {
            for (checkbox: CheckBox in checkboxList) {
                checkbox.isChecked = false
            }
        }

        val linearLayoutF = View.inflate(this, R.layout.theme_item, null)
        val layoutParamsF = LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2px(66))
        (linearLayoutF.findViewById(R.id.group_name) as TextView?)?.text = "十里桃林"
        (linearLayoutF.findViewById(R.id.group_image) as ImageView?)?.setImageResource(R.drawable.baiyinghua)
        var checkboxF = linearLayoutF.findViewById(R.id.view_checkbox) as CheckBox
        checkboxF.visibility = View.VISIBLE
        layMain?.addView(linearLayoutF, layoutParamsF)
        checkboxList.add(checkboxF)
        checkboxF.setOnCheckedChangeListener({compoundButton, b ->
            //存
            if (b) {
                checkboxList[1].isClickable = false
                XpConfig.listviewPath = getExternalFilesDir("").absolutePath + "/colorful/baiyinghua.png"
                XpConfig.saveList(this)
                scrollView?.setBackgroundResource(R.drawable.baiyinghua)
                XpConfig.themePath = getExternalFilesDir("").absolutePath + "/colorful/baiyinghua.ini"
            } else {
                XpConfig.themePath = ""
            }
            XpConfig.saveTheme(this)
        })

        val linearLayout = View.inflate(this, R.layout.theme_item, null)
        val layoutParams = LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2px(66))
        (linearLayout.findViewById(R.id.group_name) as TextView?)?.text = "我心飞扬"
        (linearLayout.findViewById(R.id.group_image) as ImageView?)?.setImageResource(R.drawable.lanbojini)
        var checkbox = linearLayout.findViewById(R.id.view_checkbox) as CheckBox
        checkbox.visibility = View.VISIBLE
        layMain?.addView(linearLayout, layoutParams)
        checkboxList.add(checkbox)

        checkbox.setOnCheckedChangeListener({compoundButton, b ->
            //存
            if (b) {
                checkboxList[0].isClickable = false
                XpConfig.listviewPath = getExternalFilesDir("").absolutePath + "/colorful/lanbojini.png"
                XpConfig.saveList(this)
                scrollView?.setBackgroundResource(R.drawable.lanbojini)
                XpConfig.themePath = getExternalFilesDir("").absolutePath + "/colorful/lanbojini.ini"
            } else {
                XpConfig.themePath = ""
            }
            XpConfig.saveTheme(this)
        })
    }

    private fun initBottomBar() {
        bottomBar?.setBackgroundColor(XpConfig.bottomBarColor)
        var backBitmap = BitmapFactory.decodeFile(XpConfig.bottomBarPath)
        val drawable = BitmapDrawable(backBitmap)
        bottomBar?.background = drawable
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.chkDarkStatusBar -> XpConfig.darkerStatusBar = chkDarkStatusBar!!.isChecked
            R.id.chkDarkStatusBarText -> XpConfig.darkStatusBarText = chkDarkStatusBarText!!.isChecked
            R.id.fabThemes -> {
                startActivity(Intent(this, ThemeListActivity::class.java))
                //startActivity(Intent(this, MyReactActivity::class.java))
            }
            R.id.fabFeedback -> {
                startActivity(Intent(this, FeedbackActivity::class.java))
            }
            R.id.fabAbout -> {
                /*val intent = Intent(Intent.ACTION_VIEW)
                val url = Uri.parse("http://www.jianshu.com/p/6fa82e3cfe00")
                intent.data = url
                startActivity(intent)*/

                startActivity(Intent(this, WebActivity::class.java))
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

}
