package com.rarnu.tophighlight

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface.OnClickListener
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig

class MainActivity : Activity() {

    private var layMain: LinearLayout? = null
    private var pref: SharedPreferences? = null
    private var toolBar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.initDisplayMetrics(this, windowManager)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage)//main
        pref = getSharedPreferences(XpConfig.PREF, if (Build.VERSION.SDK_INT < 24) 1 else 0)
        layMain = findViewById(R.id.layMain) as LinearLayout?
        toolBar = findViewById(R.id.first_toolbar) as Toolbar?
        toolBar?.setOnClickListener { view -> this@MainActivity.showDialog(view) }
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

    fun showDialog(view: View) {
        var colorSelectListener = OnColorSelectedListener {
            // TODO: on color selected
        }
        var pickerClickListener = ColorPickerClickListener { dialogInterface, selectColor, ints ->
            view.setBackgroundColor(selectColor)
        }

        var clickListener = OnClickListener { dialogInterface, i -> }
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(Color.parseColor("#ffffff"))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(colorSelectListener)
                .setPositiveButton("ok", pickerClickListener)
                .setNegativeButton("cancel", clickListener)
                .build()
                .show();
    }

}
