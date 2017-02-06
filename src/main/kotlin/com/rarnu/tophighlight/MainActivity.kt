package com.rarnu.tophighlight

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface.OnClickListener
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder


class MainActivity : Activity() {//, ColorItemView.ColorItemViewDelegate


    private var layMain: LinearLayout? = null
    private var _colorItem: ColorItemView? = null
    private var _colorIdx = -1
    private var _colorType = 0      // 0: default 1: press
    private var _selectedPalette: String? = null
    private var pref: SharedPreferences? = null
    private var toolBar : Toolbar? = null
    private var scrollView : NestedScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.initDisplayMetrics(this, windowManager)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.firstpage)//main
        pref = getSharedPreferences(Consts.PREF, if (Build.VERSION.SDK_INT < 24) 1 else 0)
        layMain = findViewById(R.id.layMain) as LinearLayout?
        toolBar = findViewById(R.id.first_toolbar) as Toolbar?
        toolBar?.setOnClickListener { view -> this.showDialog(view) }

        initScrollView()
    }

    fun initScrollView() {
        //scrollView = findViewById(R.id.first_scrollview) as NestedScrollView?
        var columnView = ColumnView(this, R.drawable.mac, "mac")
        columnView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layMain?.addView(columnView)
    }

    /*fun initColorItem() {
        (0..9).forEach {
            val colorItem = ColorItemView(this)
            colorItem.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2px(80))
            colorItem.setTitleIndex(it)
            colorItem.setDefColor(pref!!.getInt("default_$it", Consts.COLOR_DEFAULT_ARR[it]))
            colorItem.setPressColor(pref!!.getInt("highlight_$it", Consts.COLOR_HIGHLIGHT_ARR[it]))
            colorItem.delegate = this
            layMain?.addView(colorItem)
        }
    }*/

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

    /*override fun onSelectDefaultColorClicked(sender: ColorItemView?, idx: Int) {
        _colorItem = sender
        _colorIdx = idx
        _colorType = 0
        val d = ColorUtils.createColorDialog(this)
        d.selectPaletteId(_selectedPalette)
        d.show(fragmentManager, "")
    }

    override fun onSelectPressColorClicked(sender: ColorItemView?, idx: Int) {
        _colorItem = sender
        _colorIdx = idx
        _colorType = 1
        val d = ColorUtils.createColorDialog(this)
        d.selectPaletteId(_selectedPalette)
        //d.show(supportFragmentManager, "")
    }

    fun onColorChanged(color: Int, paletteId: String?, colorName: String?, paletteName: String?) {
        _selectedPalette = paletteId
        when (_colorType) {
            0 -> {
                _colorItem?.setDefColor(color)
                pref?.edit()?.putInt("default_$_colorIdx", color)?.apply()
            }
            1 -> {
                _colorItem?.setPressColor(color)
                pref?.edit()?.putInt("highlight_$_colorIdx", color)?.apply()
            }
        }
    }

    fun onColorDialogCancelled() {
        _selectedPalette = null
        when (_colorType) {
            0 -> {
                _colorItem?.setDefColor(Consts.COLOR_DEFAULT)
                pref?.edit()?.putInt("default_$_colorIdx", Consts.COLOR_DEFAULT)?.apply()
            }
            1 -> {
                _colorItem?.setPressColor(Consts.COLOR_HIGHLIGHT)
                pref?.edit()?.putInt("highlight_$_colorIdx", Consts.COLOR_HIGHLIGHT)?.apply()
            }
        }
    }

    fun onSelectDefaultColorLongClicked(sender: ColorItemView?, idx: Int) {
        _colorItem = sender
        _colorIdx = idx
        _colorType = 0
        val txt = TextView(this)
        txt.hint = getString(R.string.alert_text_placeholder)
        AlertDialog.Builder(this).setView(txt).setPositiveButton(R.string.alert_ok, { dialog, which ->
            val t = txt.text.toString()
            if (t.trim() == "" || !t.startsWith("#")) {
                return@setPositiveButton
            }
            val color = Color.parseColor(t)
            _colorItem?.setDefColor(color)
            pref?.edit()?.putInt("default_$_colorIdx", color)?.apply()
        }).setNegativeButton(R.string.alert_cancel, { dialog, which ->
            _colorItem?.setDefColor(Consts.COLOR_DEFAULT)
            pref?.edit()?.putInt("default_$_colorIdx", Consts.COLOR_DEFAULT)?.apply()
        }).show()
    }

    fun onSelectPressColorLongClicked(sender: ColorItemView?, idx: Int) {
        _colorItem = sender
        _colorIdx = idx
        _colorType = 1
        val txt = TextView(this)
        txt.hint = getString(R.string.alert_text_placeholder)
        AlertDialog.Builder(this).setView(txt).setPositiveButton(R.string.alert_ok, { dialog, which ->
            val t = txt.text.toString()
            if (t.trim() == "" || !t.startsWith("#")) {
                return@setPositiveButton
            }
            val color = Color.parseColor(t)
            _colorItem?.setPressColor(color)
            pref?.edit()?.putInt("highlight_$_colorIdx", color)?.apply()
        }).setNegativeButton(R.string.alert_cancel, { dialog, which ->
            _colorItem?.setDefColor(Consts.COLOR_DEFAULT)
            pref?.edit()?.putInt("highlight_$_colorIdx", Consts.COLOR_DEFAULT)?.apply()
        }).show()
    }*/

    fun showDialog(view : View) {
        var colorSelectListener = OnColorSelectedListener {  }
        var pickerClickListener = ColorPickerClickListener { dialogInterface, selectColor, ints ->
            view.setBackgroundColor(selectColor)
        }

        var clickListener = OnClickListener { dialogInterface, i ->  }
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(Color.parseColor("#ffffff"))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(colorSelectListener)
//                .setOnColorSelectedListener(new OnColorSelectedListener() {
//                    @Override
//                    public void onColorSelected(int selectedColor) {
//                        toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
//                    }
//                })
                .setPositiveButton("ok", pickerClickListener)
                .setNegativeButton("cancel", clickListener)
                .build()
                .show();
    }

    fun changeBackgroundColor(color : Int) {

    }

}
