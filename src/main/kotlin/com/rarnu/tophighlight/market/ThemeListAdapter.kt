package com.rarnu.tophighlight.market

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.rarnu.tophighlight.ThemePreviewView
import com.rarnu.tophighlight.api.ThemeINI
import com.rarnu.tophighlight.diy.PayActivity
import com.rarnu.tophighlight.util.Constants
import com.rarnu.tophighlight.util.UIUtils

/**
 * Created by rarnu on 2/28/17.
 */
class ThemeListAdapter : BaseAdapter {

    private var _ctx: Activity? = null
    private var _list: MutableList<ThemeINI>? = null
    private var mViewHolder: ViewHolder ? =null
    private var screenW = 0
    private var screenH = 0
    private var selectedPos = 0

    constructor(ctx: Activity, list: MutableList<ThemeINI>?): super() {
        _ctx = ctx
        _list = list
//        UIUtils.initDisplayMetrics(ctx, windowManager)
//        screenW = UIUtils
        screenH = UIUtils.height()
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
            //view = View.inflate(_ctx, R.layout.adapter_preview, null)
            mViewHolder = ViewHolder()
            mViewHolder!!.mDynamicPreview = DynamicPreview(_ctx!!, screenH / 3)
            //mViewHolder!!.mThemePreview = view.findViewById(R.id.vPreview) as ThemePreviewView?
            view = mViewHolder!!.mDynamicPreview

            view?.setTag(mViewHolder)
        }
        mViewHolder!!.mDynamicPreview!!.layoutParams?.height = screenH / 3
        mViewHolder!!.mDynamicPreview!!.setThemePreview(_list!![position])

        mViewHolder!!.mDynamicPreview!!.setOnClickListener { v ->
            /*val popup = PopupWindow(v, UIUtils.dip2px(100), UIUtils.dip2px(160))
            //popup.setBackgroundDrawable(ColorDrawable(0x00000000));
            popup.isOutsideTouchable = true
            popup.showAtLocation(convertView, Gravity.CENTER, 0, 0)*/
            var bigPreV = ThemePreviewView(_ctx!!) as ThemePreviewView
            bigPreV.setThemePreview(_list!![position])
            AlertDialog.Builder(_ctx).setTitle(_list!![position].themeName).setView(bigPreV)
                    .setPositiveButton("使用", DialogInterface.OnClickListener { dialogInterface, i ->
                        selectedPos = position
                        var intent = Intent(_ctx, PayActivity::class.java)
                        intent.putExtra("much", 1.0)
                        _ctx!!.startActivityForResult(intent, Constants.REQUEST_CODE_PAY)
                    })
                    .show()
        }

        return view
    }

    public fun getSelectedPos() : Int {
        return selectedPos
    }

    override fun getItem(position: Int): Any? = _list!![position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = _list!!.size

    class ViewHolder {
        //var mThemePreview: ThemePreviewView? = null

        var mDynamicPreview : DynamicPreview? = null
    }
}