package com.rarnu.tophighlight.market

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
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
    private var screenW = 0
    private var screenH = 0

    constructor(ctx: Context, list: MutableList<ThemeINI>?): super() {
        _ctx = ctx
        _list = list
//        UIUtils.initDisplayMetrics(ctx, windowManager)
//        screenW = UIUtils
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

        mViewHolder!!.mThemePreview!!.setOnClickListener { v ->
            /*val popup = PopupWindow(v, UIUtils.dip2px(100), UIUtils.dip2px(160))
            //popup.setBackgroundDrawable(ColorDrawable(0x00000000));
            popup.isOutsideTouchable = true
            popup.showAtLocation(convertView, Gravity.CENTER, 0, 0)*/
            var bigPreV = ThemePreviewView(_ctx!!) as ThemePreviewView
            bigPreV.setThemePreview(_list!![position])
            Toast.makeText(_ctx, "what the fuck", Toast.LENGTH_SHORT).show()
            AlertDialog.Builder(_ctx).setTitle("自定义布局").setView(bigPreV).show()
        }

        view?.setOnClickListener { v ->
            AlertDialog.Builder(_ctx).setTitle("自定义布局").setView(v).show()
        }
        return view
    }

    override fun getItem(position: Int): Any? = _list!![position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = _list!!.size

    class ViewHolder {
        var mThemePreview: ThemePreviewView? = null
    }
}