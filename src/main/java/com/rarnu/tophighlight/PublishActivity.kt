package com.rarnu.tophighlight

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.market.LocalTheme
import com.rarnu.tophighlight.ui.CustomToolBar


class PublishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)

        var toolBar = findViewById(R.id.publish_toolbar) as CustomToolBar?
        setSupportActionBar(toolBar)
        setTitle(R.string.diy_theme)
        /*if(getSupportActionBar() != null)
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)*/

        loadThemeList()
    }


    private fun loadThemeList() {
        val ini = LocalTheme.themeFlower as WthApi.ThemeINI
        var themePreview = findViewById(R.id.vPreview) as ThemePreviewView
        themePreview.setThemePreview(ini)
    }
}
