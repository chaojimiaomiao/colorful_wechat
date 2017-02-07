package com.rarnu.tophighlight

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig

/**
 * Created by zhaibingjie on 17/1/30.
 */

class ColumnView(ctx: Context, private val resourceId: Int, private val textString: String, private val key: String) : RelativeLayout(ctx) {

    init {
        initView()
    }

    private fun initView() {
        val linearLayout = View.inflate(context, R.layout.column_item, null)
        with(linearLayout) {
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2px(50))
            addView(this@with)
            setBackgroundColor(when (key) {
                XpConfig.KEY_MAC_COLOR -> XpConfig.macColor
                XpConfig.KEY_TOP_READER_COLOR -> XpConfig.readerColor
                else -> Color.TRANSPARENT
            })
            (findViewById(R.id.item_text) as TextView?)?.text = textString
            (findViewById(R.id.item_image) as ImageView?)?.setImageResource(resourceId)
            setOnClickListener { UIUtils.showDialog(context, getPickerClickListener(this@with), false) }
            setOnLongClickListener {
                // UIUtils.showDialog(context, getPickerClickListener(this@with), true)
                true
            }
        }
    }

    fun getPickerClickListener(view: View): ColorPickerClickListener = ColorPickerClickListener { dialogInterface, selectColor, integers ->
        view.setBackgroundColor(selectColor)
        when (key) {
            XpConfig.KEY_MAC_COLOR -> XpConfig.macColor = selectColor
            XpConfig.KEY_TOP_READER_COLOR -> XpConfig.readerColor = selectColor
        }
        XpConfig.save(context)
    }

}
