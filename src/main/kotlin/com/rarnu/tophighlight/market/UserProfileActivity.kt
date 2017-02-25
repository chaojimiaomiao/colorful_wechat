package com.rarnu.tophighlight.market

import android.os.Bundle
import com.rarnu.tophighlight.R

/**
 * Created by rarnu on 2/25/17.
 */
class UserProfileActivity : BaseMarkerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        actionBar.setTitle(R.string.view_user_profile)
    }

}