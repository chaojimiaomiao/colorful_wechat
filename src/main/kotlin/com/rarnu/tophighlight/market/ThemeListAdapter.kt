package com.rarnu.tophighlight.market

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.ThemePreviewView
import com.rarnu.tophighlight.api.ThemeINI
import com.rarnu.tophighlight.util.UIUtils

/**
 * Created by rarnu on 2/28/17.
 */
class ThemeListAdapter : BaseAdapter {

    private var _ctx: Context? = null
    private var _list: MutableList<ThemeINI>? = null
    private var mViewHolder: ViewHolder ? =null

    constructor(ctx: Context, list: MutableList<ThemeINI>?): super() {
        _ctx = ctx
        _list = list
    }

    fun setList(list: MutableList<ThemeINI>?) {
        _list = list
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        if (view != null) {
            mViewHolder = view.getTag() as ViewHolder?
        } else {
            view = View.inflate(_ctx, R.layout.adapter_preview, null)
            mViewHolder = ViewHolder()
            mViewHolder!!.mThemePreview = view.findViewById(R.id.vPreview) as ThemePreviewView?

            view.setTag(mViewHolder)
        }
        mViewHolder!!.mThemePreview!!.layoutParams?.height = UIUtils.height() / 3
        mViewHolder!!.mThemePreview!!.setThemePreview(_list!![position])


        return view
    }

    override fun getItem(position: Int): Any? = _list!![position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = _list!!.size

    class ViewHolder {
        var mThemePreview: ThemePreviewView? = null
    }
}