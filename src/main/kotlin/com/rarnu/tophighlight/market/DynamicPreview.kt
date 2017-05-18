package com.rarnu.tophighlight.market

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.api.ThemeINI
import com.rarnu.tophighlight.util.UIUtils

/**
 * Created by zhaibingjie on 2017/5/18.
 */
class DynamicPreview : LinearLayout {
    private var mContext: Context? = null
    private var mHei = 0

    private var layStatusbar: RelativeLayout? = null
    private var tvStatusbar: TextView? = null
    private var layActionbar: TextView? = null
    private var layMac: TextView? = null
    private var layReading: TextView? = null
    private var layTop0: TextView? = null
    private var vHighColor0: View? = null
    private var layTop1: TextView? = null
    private var vHighColor1: View? = null
    private var layTop2: TextView? = null
    private var vHighColor2: View? = null

    private var imgBottomBar: ImageView? = null

    constructor(ctx: Context, previeHeight: Int): super(ctx) {
        mContext = ctx
        mHei = previeHeight
        init(previeHeight)
    }

    private fun init(mHeight: Int) {
        layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight)
        orientation = VERTICAL

        tvStatusbar = TextView(mContext)
        tvStatusbar!!.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight/50)
        tvStatusbar!!.setText("   状态栏")
        tvStatusbar!!.textSize = 4f
        addView(tvStatusbar)

        layActionbar = TextView(mContext)
        layActionbar!!.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight/15)
        layActionbar!!.setText("   微信")
        layActionbar!!.textSize = 9f
        addView(layActionbar)
        addView(getDivideLine())

        layMac = TextView(mContext)
        layMac!!.setText("  mac微信已登录")
        layMac!!.textSize = 7F
        layMac!!.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight/24)
        addView(layMac)
        addView(getDivideLine())

        layReading = TextView(mContext)
        layReading!!.setText("  正在浏览置顶文章")
        layReading!!.textSize = 7F
        layReading!!.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight/24)
        addView(layReading)
        addView(getDivideLine())

        layTop0 = TextView(mContext)
        layTop0!!.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight/12)
        layTop0!!.setText("  置顶栏目0")
        layTop0!!.textSize = 7F
        addView(layTop0)
        addView(getDivideLine())

        layTop1 = TextView(mContext)
        layTop1!!.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight/12)
        layTop1!!.textSize = 7F
        layTop1!!.setText("  置顶栏目1")
        addView(layTop1)
        addView(getDivideLine())

        layTop2 = TextView(mContext)
        layTop2!!.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mHeight/12)
        layTop2!!.textSize = 7F
        layTop2!!.setText("  置顶栏目2")
        addView(layTop2)
        addView(getDivideLine())


        var view = View(mContext)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                mHeight - mHeight/50 - mHeight/15 - mHeight/24 *2 - mHeight/12 *3 - mHeight/13)
        addView(view)

        imgBottomBar = ImageView(mContext)
        imgBottomBar!!.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight/13)
        imgBottomBar!!.setImageResource(R.drawable.bottombar)
        addView(imgBottomBar)
    }

    fun setThemePreview(t: ThemeINI?) {
        if (t != null) {
            if (t.darkerStatusBar) {
                tvStatusbar?.setBackgroundColor(UIUtils.getDarkerColor(t.statusBarColor, 0.85f))
            } else {
                tvStatusbar?.setBackgroundColor(t.statusBarColor)
            }
            tvStatusbar?.setTextColor(if (t.darkStatusBarText) Color.BLACK else Color.WHITE)

            layActionbar?.setBackgroundColor(t.statusBarColor)
            layMac?.setBackgroundColor(t.macColor)
            layReading?.setBackgroundColor(t.readerColor)
            //imgBottomBar?.setBackgroundColor(t.bottomBarColor)

            layTop0?.setBackgroundColor(t.topColors0)
            //vHighColor0?.setBackgroundColor(t.topPressColors0)
            layTop1?.setBackgroundColor(t.topColors1)
            //vHighColor1?.setBackgroundColor(t.topPressColors1)
            layTop2?.setBackgroundColor(t.topColors2)
            //vHighColor2?.setBackgroundColor(t.topPressColors2)

            UIUtils.backviewBindPath(this, t.listPath)
            UIUtils.backviewBindPath(imgBottomBar!!, t.bottomBarPath)
            UIUtils.backviewBindPath(layActionbar!!, t.statusBarPath)
        }
    }

    fun getDivideLine() : View {
        var lineV = View(mContext)
        lineV.setBackgroundColor(resources.getColor(R.color.gray))
        lineV.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1)
        return lineV
    }


    /*constructor(ctx: Context, attrs: AttributeSet?, defStyle: Int): super(ctx, attrs, defStyle) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet?): super(ctx, attrs) {
        init()
    }*/
}