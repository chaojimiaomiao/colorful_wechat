package com.rarnu.tophighlight

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bingjie.colorpicker.builder.ColorPickerClickListener
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig

/**
 * Created by zhaibingjie on 17/2/9.
 *  private val backDraw: BitmapDrawable,
 */
class GroupColumn (ctx: Context, private val textString: String, private val key: String) : RelativeLayout(ctx) {
    init {
        initView()
    }

    private fun initView() {
        val linearLayout = View.inflate(context, R.layout.group_item, null)
        with(linearLayout) {
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2px(66))
            addView(this@with)

            if (key.contains(XpConfig.KEY_DING)) {
                var number = key.last().toInt()
                setBackgroundColor(XpConfig.topColors[number - 48])
                this@with.findViewById(R.id.view_highcolor).setBackgroundColor(XpConfig.topPressColors[number - 48])
            } else if (key.contains(XpConfig.KEY_NORMAL)) {
                setBackgroundColor(XpConfig.normalColor)
                this@with.findViewById(R.id.view_highcolor).setBackgroundColor(XpConfig.normalPressColor)
            }

            (findViewById(R.id.group_name) as TextView?)?.text = textString
            (findViewById(R.id.group_image) as ImageView?)?.setImageResource(R.drawable.group_avatar)
            setOnClickListener { UIUtils.showDialog(context, getPickerClickListener(this@with)) }
            setOnLongClickListener {
                UIUtils.showDialog(context, getPickerClickListener(this@with.findViewById(R.id.view_highcolor), true), true)
                true
            }
        }
    }

    fun getPickerClickListener(view: View, longClick: Boolean = false): ColorPickerClickListener = ColorPickerClickListener {selectColor, integers ->
        view.setBackgroundColor(selectColor)
        if (!longClick) {
            if (key.contains(XpConfig.KEY_DING)) {
                var number = key.last().toInt()
                XpConfig.topColors[number - 48] = selectColor
            } else if (key.contains(XpConfig.KEY_NORMAL)) {
                XpConfig.normalColor = selectColor
            }
        } else {
            if (key.contains(XpConfig.KEY_PRESS_DING)) {
                var number = key.last().toInt()
                XpConfig.topPressColors[number - 48] = selectColor
            } else if (key.contains(XpConfig.KEY_NORMAL)) {
                XpConfig.normalPressColor = selectColor
            }
        }
        XpConfig.saveGroup(context)
    }
}