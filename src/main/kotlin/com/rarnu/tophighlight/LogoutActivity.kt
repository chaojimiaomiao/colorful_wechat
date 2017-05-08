package com.rarnu.tophighlight

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.rarnu.tophighlight.api.LocalApi
import com.rarnu.tophighlight.api.User

class LogoutActivity : Activity() {
    private var account: TextView ?= null
    private var nickname: TextView ?= null
    private var mail: TextView ?= null
    private var password: TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        account = findViewById(R.id.logout_account) as TextView
        nickname = findViewById(R.id.logout_nickname) as TextView
        mail = findViewById(R.id.logout_mail) as TextView
        password = findViewById(R.id.logout_password) as TextView

        var mUsr = LocalApi.curUser as User
        account?.text = mUsr.account
        nickname?.text = mUsr.nickname
        mail?.text = mUsr.email
        //password?.text = mUsr.

        findViewById(R.id.logout).setOnClickListener {
            LocalApi.clear()
            Toast.makeText(this, "退出登录成功!", Toast.LENGTH_SHORT).show()
        }
    }
}
