package com.rarnu.tophighlight

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.rarnu.tophighlight.api.LocalApi
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.market.LocalTheme
import com.rarnu.tophighlight.market.UserLoginRegisterActivity
import com.rarnu.tophighlight.ui.CustomToolBar


class PublishActivity : AppCompatActivity() {

    private var ini : WthApi.ThemeINI ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)

        var toolBar = findViewById(R.id.publish_toolbar) as CustomToolBar?
        setSupportActionBar(toolBar)
        setTitle(R.string.diy_theme)
        /*if(getSupportActionBar() != null)
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)*/


        loadTheme()

        var publishBtn = findViewById(R.id.btn_publish)

        findViewById(R.id.publish_login_btn).setOnClickListener {
            startActivity(Intent(this, UserLoginRegisterActivity::class.java))
        }

        findViewById(R.id.btn_publish).setOnClickListener {
            var ret = WthApi.themeUpload(LocalApi.userId, ini?.themeName, "", ini?.listPath?.replace(".png", ".ini"))
            Log.e("", "发布结果" + ret)
        }

    }

    private fun loadTheme() {
        ini = LocalTheme.themeFlower
        var themePreview = findViewById(R.id.vPreview) as ThemePreviewView
        themePreview.setThemePreview(ini)
    }
}
