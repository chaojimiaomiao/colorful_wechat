package com.rarnu.tophighlight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by rarnu on 1/26/17.
 */
class ColorItemView : RelativeLayout, View.OnClickListener, View.OnLongClickListener {


    interface ColorItemViewDelegate {
        fun onSelectDefaultColorClicked(sender: ColorItemView?, idx: Int)
        fun onSelectPressColorClicked(sender: ColorItemView?, idx: Int)
        fun onSelectDefaultColorLongClicked(sender: ColorItemView?, idx: Int)
        fun onSelectPressColorLongClicked(sender: ColorItemView?, idx: Int)
    }

    private var tvTitle: TextView? = null
    private var vColorDefault: RelativeLayout? = null
    private var vDef: View? = null
    private var vColorPress: RelativeLayout? = null
    private var vPress: View? = null

    var index = -1
    var delegate: ColorItemViewDelegate? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attr: AttributeSet?) : super(context, attr) {
        init()
    }

    constructor(context: Context, attr: AttributeSet?, defStyle: Int) : super(context, attr, defStyle) {
        init()
    }

    private fun init() {
        val v = LayoutInflater.from(context).inflate(R.layout.view_color_item, null)
        addView(v)
        tvTitle = v.findViewById(R.id.tvTitle) as TextView?
        vColorDefault = v.findViewById(R.id.vColorDefault) as RelativeLayout?
        vDef = v.findViewById(R.id.vDef)
        vColorPress = v.findViewById(R.id.vColorPress) as RelativeLayout?
        vPress = v.findViewById(R.id.vPress)
        vColorDefault?.setOnClickListener(this)
        vColorPress?.setOnClickListener(this)
        vColorDefault?.setOnLongClickListener(this)
        vColorPress?.setOnLongClickListener(this)

        vDef?.setBackgroundColor(Consts.COLOR_DEFAULT)
        vPress?.setBackgroundColor(Consts.COLOR_HIGHLIGHT)
    }

    fun setTitleIndex(idx: Int) {
        index = idx
        tvTitle?.text = context.getString(R.string.view_item, idx + 1)
    }

    fun setDefColor(c: Int) = vDef?.setBackgroundColor(c)

    fun setPressColor(c: Int) = vPress?.setBackgroundColor(c)

    override fun onClick(v: View) {
        when(v.id) {
            R.id.vColorDefault -> delegate?.onSelectDefaultColorClicked(this, index)
            R.id.vColorPress -> delegate?.onSelectPressColorClicked(this, index)
        }
    }

    override fun onLongClick(v: View): Boolean {
        when(v.id) {
            R.id.vColorDefault -> delegate?.onSelectDefaultColorLongClicked(this, index)
            R.id.vColorPress -> delegate?.onSelectPressColorLongClicked(this, index)
        }
        return true
    }
}