package com.rarnu.tophighlight.market

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.api.LocalApi
import com.rarnu.tophighlight.api.WthApi
import kotlin.concurrent.thread

/**
 * Created by rarnu on 2/25/17.
 */
class UserLoginRegisterActivity : BaseMarkerActivity(), View.OnClickListener {

    private var layLogin: RelativeLayout? = null
    private var layRegister: RelativeLayout? = null

    // login
    private var loginAccount: EditText? = null
    private var loginPassword: EditText? = null
    private var btnLogin: Button? = null
    private var btnSwitchRegister: Button? = null

    // register
    private var regAccount: EditText? = null
    private var regPassword: EditText? = null
    private var regRepeatPassword: EditText? = null
    private var regNickname: EditText? = null
    private var regEmail: EditText? = null
    private var btnRegister: Button? = null
    private var btnSwitchLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        actionBar.setTitle(R.string.view_account_operation)

        layLogin = findViewById(R.id.layLogin) as RelativeLayout?
        layRegister = findViewById(R.id.layRegister) as RelativeLayout?

        layLogin?.visibility = View.VISIBLE
        layRegister?.visibility = View.GONE

        // login
        loginAccount = findViewById(R.id.etAccount) as EditText?
        loginPassword = findViewById(R.id.etPassword) as EditText?
        btnLogin = findViewById(R.id.btnLogin) as Button?
        btnSwitchRegister = findViewById(R.id.btnSwitchRegister) as Button?

        // register
        regAccount = findViewById(R.id.etRegAccount) as EditText?
        regPassword = findViewById(R.id.etRegPassword) as EditText?
        regRepeatPassword = findViewById(R.id.etRegRepeatPassword) as EditText?
        regNickname = findViewById(R.id.etRegNickname) as EditText?
        regEmail = findViewById(R.id.etRegEmail) as EditText?
        btnRegister = findViewById(R.id.btnRegister) as Button?
        btnSwitchLogin = findViewById(R.id.btnSwitchLogin) as Button?

        // events
        btnLogin?.setOnClickListener(this)
        btnSwitchRegister?.setOnClickListener(this)
        btnRegister?.setOnClickListener(this)
        btnSwitchLogin?.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLogin -> login()
            R.id.btnRegister -> register()
            R.id.btnSwitchRegister -> {
                layRegister?.visibility = View.VISIBLE
                layLogin?.visibility = View.GONE
            }
            R.id.btnSwitchLogin -> {
                layLogin?.visibility = View.VISIBLE
                layRegister?.visibility = View.GONE
            }
        }
    }

    val hSucc = object : Handler() {
        override fun handleMessage(msg: Message?) {
            setResult(RESULT_OK)
            finish()
            super.handleMessage(msg)
        }
    }

    val hFail = object : Handler() {
        override fun handleMessage(msg: Message?) {
            if (msg != null) {
                Toast.makeText(this@UserLoginRegisterActivity, when (msg.what) {
                    0 -> getString(R.string.toast_login_failed)
                    1 -> getString(R.string.toast_register_failed)
                    else -> ""
                }, Toast.LENGTH_SHORT)
                .show()
            }

            super.handleMessage(msg)
        }
    }

    private fun register() {
        val acc = regAccount?.text?.toString()
        val pwd = regPassword?.text?.toString()
        val repeatPwd = regRepeatPassword?.text?.toString()
        val nickname = regNickname?.text?.toString()
        val email = regEmail?.text?.toString()
        if (acc == null || acc == "" || pwd == null || pwd == "" || repeatPwd == null || repeatPwd == "" || pwd != repeatPwd) return
        thread {
            val id = WthApi.userRegister(acc, pwd, nickname, email)
            if (id != 0) {
                LocalApi.userId = id
                hSucc.sendEmptyMessage(0)
            } else {
                hFail.sendEmptyMessage(1)
            }
        }
    }

    private fun login() {
        val acc = loginAccount?.text?.toString()
        val pwd = loginPassword?.text?.toString()
        if (acc == null || acc == "" || pwd == null || pwd == "") return
        thread {
            val user = WthApi.userLogin(acc, pwd)
            if (user != null && user.id != 0) {
                LocalApi.userId = user.id
                hSucc.sendEmptyMessage(0)
            } else {
                hFail.sendEmptyMessage(0)
            }
        }
    }
}