package com.rarnu.tophighlight

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.rarnu.tophighlight.api.ThemeINI
import com.rarnu.tophighlight.util.UIUtils

/**
 * Created by rarnu on 2/28/17.
 *
 * 此处用来在自定义主题这显示,自定义主题以图片为主.
 * 显示三个top column
 */
class ThemePreviewView : RelativeLayout {

    private var layStatusbar: RelativeLayout? = null
    private var tvStatusbar: TextView? = null
    private var layActionbar: RelativeLayout? = null
    private var layMac: LinearLayout? = null
    private var layReading: LinearLayout? = null
    private var layTop0: LinearLayout? = null
    private var vHighColor0: View? = null
    private var layTop1: LinearLayout? = null
    private var vHighColor1: View? = null
    private var layTop2: LinearLayout? = null
    private var vHighColor2: View? = null

    private var imgBottomBar: ImageView? = null
    private var scrollView: ScrollView? = null

    constructor(ctx: Context, attrs: AttributeSet?, defStyle: Int): super(ctx, attrs, defStyle) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet?): super(ctx, attrs) {
        init()
    }

    constructor(ctx: Context): super(ctx) {
        init()
    }

    private fun init() {
        val layout = View.inflate(context, R.layout.preview_item, null)
        with(layout) {
            layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            addView(this@with)
            scrollView = findViewById(R.id.scroll_back) as ScrollView?
            layStatusbar = findViewById(R.id.layStatusbar) as RelativeLayout?
            tvStatusbar = findViewById(R.id.tvStatusbar) as TextView?
            layActionbar = findViewById(R.id.layActionbar) as RelativeLayout?
            layMac = findViewById(R.id.layMac) as LinearLayout?
            layReading = findViewById(R.id.layReading) as LinearLayout?
            imgBottomBar = findViewById(R.id.bottom_bar) as ImageView?
            layTop0 = findViewById(R.id.layTop0) as LinearLayout?
            vHighColor0 = findViewById(R.id.view_highcolor0)
            layTop1 = findViewById(R.id.layTop1) as LinearLayout?
            vHighColor1 = findViewById(R.id.view_highcolor1)
            layTop2 = findViewById(R.id.layTop2) as LinearLayout?
            vHighColor2 = findViewById(R.id.view_highcolor2)

        }
    }

    fun setThemePreview(t: ThemeINI?) {
        if (t != null) {
            if (t.darkerStatusBar) {
                layStatusbar?.setBackgroundColor(UIUtils.getDarkerColor(t.statusBarColor, 0.85f))
            } else {
                layStatusbar?.setBackgroundColor(t.statusBarColor)
            }
            tvStatusbar?.setTextColor(if (t.darkStatusBarText) Color.BLACK else Color.WHITE)
            layActionbar?.setBackgroundColor(t.statusBarColor)
            layMac?.setBackgroundColor(t.macColor)
            layReading?.setBackgroundColor(t.readerColor)
            imgBottomBar?.setBackgroundColor(t.bottomBarColor)

            layTop0?.setBackgroundColor(t.topColors0)
            vHighColor0?.setBackgroundColor(t.topPressColors0)
            layTop1?.setBackgroundColor(t.topColors1)
            vHighColor1?.setBackgroundColor(t.topPressColors1)
            layTop2?.setBackgroundColor(t.topColors2)
            vHighColor2?.setBackgroundColor(t.topPressColors2)

            UIUtils.backviewBindPath(scrollView!!, t.listPath)
            UIUtils.backviewBindPath(imgBottomBar!!, t.bottomBarPath)
            UIUtils.backviewBindPath(layActionbar!!, t.statusBarPath)
        }
    }
}