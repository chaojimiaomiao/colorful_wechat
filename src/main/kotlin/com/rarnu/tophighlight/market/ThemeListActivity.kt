package com.rarnu.tophighlight.market

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.ListView
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.api.LocalApi
import com.rarnu.tophighlight.api.WthApi
import kotlin.concurrent.thread

/**
 * Created by rarnu on 2/25/17.
 */
class ThemeListActivity : BaseMarkerActivity() {

    companion object {
        val MENUID_PROFILE = 1;
    }

    private var miProfile: MenuItem? = null
    private var listTheme: MutableList<WthApi.ThemeINI>? = null
    private var gvTheme: GridView? = null
    private var adapterTheme: ThemeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalApi.ctx = this
        setContentView(R.layout.activity_themelist)
        actionBar.setTitle(R.string.view_themes)
        gvTheme = findViewById(R.id.gvTheme) as GridView?

        listTheme = arrayListOf()
        adapterTheme = ThemeListAdapter(this, listTheme)
        gvTheme?.adapter = adapterTheme

        loadThemeList()
    }

    private fun loadThemeList() {
        val hTheme = object : Handler() {
            override fun handleMessage(msg: Message?) {
                adapterTheme?.setList(listTheme)
                super.handleMessage(msg)
            }
        }
        thread {
            val list = WthApi.themeGetList(1, 20, "date")
            println("list = $list")

            // TODO: download theme file and make preview
            hTheme.sendEmptyMessage(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        miProfile = menu?.add(0, MENUID_PROFILE, 1, R.string.menu_profile)
        miProfile?.setIcon(android.R.drawable.ic_menu_myplaces)
        miProfile?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            MENUID_PROFILE -> {
                if (LocalApi.userId == 0) {
                    // login / register
                    startActivityForResult(Intent(this, UserLoginRegisterActivity::class.java), 0)
                } else {
                    // user profile
                    startActivity(Intent(this, UserProfileActivity::class.java))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) return
        when(requestCode) {
            0 -> {
                // TODO: login or register callback
            }
        }
    }
}