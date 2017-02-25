package com.rarnu.tophighlight.market

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.api.LocalApi

/**
 * Created by rarnu on 2/25/17.
 */
class ThemeListActivity : BaseMarkerActivity() {

    companion object {
        val MENUID_PROFILE = 1;
    }

    private var miProfile: MenuItem? = null
    private var lvThemeList: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalApi.ctx = this
        setContentView(R.layout.activity_themelist)
        actionBar.setTitle(R.string.view_themes)

        lvThemeList = findViewById(R.id.lvThemeList) as ListView?
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