package com.rarnu.tophighlight.market

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.ThemePreviewView
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.util.UIUtils

/**
 * Created by rarnu on 2/28/17.
 */
class ThemeListAdapter : BaseAdapter {

    private var _ctx: Context? = null
    private var _list: MutableList<WthApi.ThemeINI>? = null

    constructor(ctx: Context, list: MutableList<WthApi.ThemeINI>?): super() {
        _ctx = ctx
        _list = list
    }

    fun setList(list: MutableList<WthApi.ThemeINI>?) {
        _list = list
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var v = convertView
        if (v == null) {
            v = View.inflate(_ctx, R.layout.adapter_preview, null)
        }
        v?.layoutParams?.height = UIUtils.height() / 3
        (v?.findViewById(R.id.vPreview) as ThemePreviewView?)?.setThemePreview(_list!![position])
        return v
    }

    override fun getItem(position: Int): Any? = _list!![position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = _list!!.size
}