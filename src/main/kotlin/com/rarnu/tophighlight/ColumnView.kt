package com.rarnu.tophighlight

import android.content.Context
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

class ColumnView(
        private val mContext: Context,
        mAttributeSet: AttributeSet?,
        private val mResourceId: Int,
        private val mTextString: String,
        private val mKey: String) : RelativeLayout(mContext, mAttributeSet) {

    init {
        initView()
    }

    private fun initView() {

        val linearLayout = LayoutInflater.from(context).inflate(R.layout.column_item, null) as LinearLayout
        linearLayout.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, UIUtils.dip2px(50))
        addView(linearLayout)

        when (mKey) {
            XpConfig.KEY_MAC_COLOR -> linearLayout.setBackgroundColor(XpConfig.macColor)
            XpConfig.KEY_TOP_READER_COLOR -> linearLayout.setBackgroundColor(XpConfig.readerColor)
        }

        (linearLayout.findViewById(R.id.item_text) as TextView).text = mTextString
        (linearLayout.findViewById(R.id.item_image) as ImageView).setImageResource(mResourceId)

        linearLayout.setOnClickListener {
            val pickerClickListener = getPickerClickListener(linearLayout)
            UIUtils.showDialog(mContext, pickerClickListener, false)
        }
        linearLayout.setOnLongClickListener {
            //UIUtils.INSTANCE.showDialog(mContext, pickerClickListener, true);
            false
        }
    }

    fun getPickerClickListener(view: View): ColorPickerClickListener {
        val pickerClickListener = ColorPickerClickListener { dialogInterface, selectColor, integers ->
            view.setBackgroundColor(selectColor)
            when (mKey) {
                XpConfig.KEY_MAC_COLOR -> XpConfig.macColor = selectColor
                XpConfig.KEY_TOP_READER_COLOR -> XpConfig.readerColor = selectColor
            }
            XpConfig.save(mContext)
        }
        return pickerClickListener
    }
}
