package com.rarnu.tophighlight

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.rarnu.tophighlight.api.WthApi

class FeedbackActivity : AppCompatActivity() {

    var feedbackBtn : Button ?= null
    var feedbackEdit : EditText ?= null
    var feedbackStr: String ?= null
    var versionCode: Int ?= null
    var versionName: String ?= null
    var nickNameEdit : EditText ?= null
    var emailEdit : EditText ?= null
    var email :String ? = null
    var nickname :String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        getVersion()
        feedbackEdit = findViewById(R.id.feedback_edit) as EditText
        nickNameEdit = findViewById(R.id.feedback_edit) as EditText
        emailEdit  = findViewById(R.id.feedback_edit) as EditText

        feedbackEdit!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                checkInfo()
            }
        })
        feedbackBtn = findViewById(R.id.btn_feedback_sure) as Button
        feedbackBtn?.setOnClickListener {
            WthApi.feedbackAdd(versionCode!!, 0, nickname, email, feedbackStr, "", "","")
            finish()
        }
    }

    fun checkInfo() {
        feedbackStr = feedbackEdit?.editableText.toString()
        nickname = nickNameEdit?.editableText.toString()
        email = emailEdit?.editableText.toString()
        if (feedbackStr != "" && nickname != "" && email != "") {
            feedbackBtn?.isClickable = true
            feedbackBtn?.background = getDrawable(R.drawable.bg_button_or_radius)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.main_to_schedule_out)
    }

    fun getVersion() {
        var pinfo = packageManager.getPackageInfo("com.rarnu.tophighlight", PackageManager.GET_CONFIGURATIONS)
        versionCode = pinfo.versionCode
        versionName = pinfo.versionName
    }
}