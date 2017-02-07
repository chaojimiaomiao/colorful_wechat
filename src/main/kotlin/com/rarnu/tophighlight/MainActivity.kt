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
    private var pref: SharedPreferences? = null

    private var toolBar : Toolbar? = null
    private var scrollView : NestedScrollView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.initDisplayMetrics(this, windowManager)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage)//main
        pref = getSharedPreferences(XpConfig.PREF, if (Build.VERSION.SDK_INT < 24) 1 else 0)
        XpConfig.editor = pref!!.edit()

        layMain = findViewById(R.id.layMain) as LinearLayout?
        toolBar = findViewById(R.id.first_toolbar) as Toolbar?
        toolBar?.setBackgroundColor(XpConfig.statusBarColor)
        toolBar?.setOnClickListener { view -> UIUtils.showDialog(this, ColorPickerClickListener { dialogInterface, selectColor, integers ->
            view.setBackgroundColor(selectColor)
            XpConfig.statusBarColor = selectColor
        }) }

        initScrollView()
    }

    fun initScrollView() {
        //替换掉R.id.bvj的背景色
        var columnViewMac = ColumnView(this, R.drawable.mac, "Mac微信已登录", XpConfig.KEY_MAC_COLOR)
        columnViewMac.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layMain?.addView(columnViewMac)

        //替换掉R.id.d3o的背景色
        var columnViewRead = ColumnView(this, R.drawable.reader, "正在浏览 置顶文章", XpConfig.KEY_TOP_READER_COLOR)
        columnViewRead.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layMain?.addView(columnViewRead)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.add(0, 0, 0, R.string.menu_about)
        item?.setIcon(android.R.drawable.ic_menu_info_details)
        item?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> {
                AlertDialog.Builder(this).setTitle(R.string.menu_about)
                        .setMessage(R.string.alert_text_about)
                        .setPositiveButton(R.string.alert_ok, null)
                        .show()
            }
        }
        return true
    }

    override fun onStop() {
        super.onStop()
        XpConfig.save()
    }


}
