package com.rarnu.tophighlight

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.util.UIUtils

/**
 * Created by rarnu on 2/28/17.
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
    private var layTop3: LinearLayout? = null
    private var vHighColor3: View? = null
    private var layTop4: LinearLayout? = null
    private var vHighColor4: View? = null
    private var layTop5: LinearLayout? = null
    private var vHighColor5: View? = null
    private var layTop6: LinearLayout? = null
    private var vHighColor6: View? = null
    private var layTop7: LinearLayout? = null
    private var vHighColor7: View? = null
    private var layTop8: LinearLayout? = null
    private var vHighColor8: View? = null
    private var layTop9: LinearLayout? = null
    private var vHighColor9: View? = null
    private var imgBottomBar: ImageView? = null

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
            layTop3 = findViewById(R.id.layTop3) as LinearLayout?
            vHighColor3 = findViewById(R.id.view_highcolor3)
            layTop4 = findViewById(R.id.layTop4) as LinearLayout?
            vHighColor4 = findViewById(R.id.view_highcolor4)
            layTop5 = findViewById(R.id.layTop5) as LinearLayout?
            vHighColor5 = findViewById(R.id.view_highcolor5)
            layTop6 = findViewById(R.id.layTop6) as LinearLayout?
            vHighColor6 = findViewById(R.id.view_highcolor6)
            layTop7 = findViewById(R.id.layTop7) as LinearLayout?
            vHighColor7 = findViewById(R.id.view_highcolor7)
            layTop8 = findViewById(R.id.layTop8) as LinearLayout?
            vHighColor8 = findViewById(R.id.view_highcolor8)
            layTop9 = findViewById(R.id.layTop9) as LinearLayout?
            vHighColor9 = findViewById(R.id.view_highcolor9)

        }
    }

    fun setThemePreview(t: WthApi.ThemeINI?) {
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
            layTop3?.setBackgroundColor(t.topColors3)
            vHighColor3?.setBackgroundColor(t.topPressColors3)
            layTop4?.setBackgroundColor(t.topColors4)
            vHighColor4?.setBackgroundColor(t.topPressColors4)
            layTop5?.setBackgroundColor(t.topColors5)
            vHighColor5?.setBackgroundColor(t.topPressColors5)
            layTop6?.setBackgroundColor(t.topColors6)
            vHighColor6?.setBackgroundColor(t.topPressColors6)
            layTop7?.setBackgroundColor(t.topColors7)
            vHighColor7?.setBackgroundColor(t.topPressColors7)
            layTop8?.setBackgroundColor(t.topColors8)
            vHighColor8?.setBackgroundColor(t.topPressColors8)
            layTop9?.setBackgroundColor(t.topColors9)
            vHighColor9?.setBackgroundColor(t.topPressColors9)

        }
    }
}