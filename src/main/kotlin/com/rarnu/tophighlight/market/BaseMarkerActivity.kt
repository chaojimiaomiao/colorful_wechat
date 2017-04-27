package com.rarnu.tophighlight.market

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import com.rarnu.tophighlight.util.UIUtils

/**
 * Created by rarnu on 2/26/17.
 */
open class BaseMarkerActivity : Activity() {

    companion object {
        val STATUABAR_COLOR = 0xffffc7c8.toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setBackgroundDrawable(ColorDrawable(STATUABAR_COLOR))
        window.statusBarColor = UIUtils.getDarkerColor(STATUABAR_COLOR, 0.85f)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}