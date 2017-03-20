package com.rarnu.tophighlight

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import com.rarnu.tophighlight.xposed.XpConfig

class TopListActivity : Activity() {

    private var layMain: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_list)

        layMain = findViewById(R.id.layMain) as LinearLayout?

        initDingGroup(10)
    }

    private fun initDingGroup(nums :Int) {//bitmapList : ArrayList<Bitmap>,
        (0..(nums -1)).forEach {
            // BitmapDrawable(bitmapList[it]),
            val colorItem = GroupColumn(this, getString(R.string.ding_column) +"  $it", "${XpConfig.KEY_DING}$it")
            colorItem.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layMain?.addView(colorItem)
        }

    }
}
