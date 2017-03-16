package com.rarnu.tophighlight

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
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
    var addPic : ImageView ?= null
    var pic1 :ImageView ?= null
    var pic2 :ImageView ?= null
    var pic3 :ImageView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        getVersion()
        feedbackEdit = findViewById(R.id.feedback_edit) as EditText
        nickNameEdit = findViewById(R.id.feedback_edit) as EditText
        emailEdit  = findViewById(R.id.feedback_edit) as EditText
        addPic = findViewById(R.id.add_pic) as ImageView
        addPic?.setOnClickListener {
            choosePic()
        }

        pic1 = findViewById(R.id.pic1) as ImageView
        pic2 = findViewById(R.id.pic2) as ImageView
        pic3 = findViewById(R.id.pic3) as ImageView

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
            var path1 = pic1?.tag as String?
            var path2 = pic2?.tag as String?
            var path3 = pic3?.tag as String?
            var success = WthApi.feedbackAdd(versionCode!!, 0, nickname, email, feedbackStr, path1, path2, path3) as Boolean
            if (success) {
                Toast.makeText(this, "反馈成功!谢谢^_^", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "反馈失败,重新试试(⊙﹏⊙)b", Toast.LENGTH_LONG).show()
            }
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

    fun choosePic() {
        val intent = Intent(Intent.ACTION_PICK, null)
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 2)
    }

    public override fun onActivityResult(requestCode: Int,
                                         resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
                try {
                    val uri = data.data
                    val absolutePath = getAbsolutePath(this, uri)
                    if (pic1?.tag == null) {
                        pic1?.setImageURI(uri)
                        pic1?.tag = absolutePath
                    } else if (pic2?.tag == null) {
                        pic2?.setImageURI(uri)
                        pic2?.tag = absolutePath
                    } else if (pic3?.tag == null) {
                        pic3?.setImageURI(uri)
                        pic3?.tag = absolutePath
                        addPic?.visibility = View.INVISIBLE
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun getAbsolutePath(context: Context,
                        uri: Uri?): String? {
        if (null == uri) return null
        val scheme = uri.scheme
        var data: String? = null
        if (scheme == null)
            data = uri.path
        else if (ContentResolver.SCHEME_FILE == scheme) {
            data = uri.path
        } else if (ContentResolver.SCHEME_CONTENT == scheme) {
            val cursor = context.contentResolver.query(uri,
                    arrayOf(MediaStore.Images.ImageColumns.DATA),
                    null, null, null)
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    val index = cursor.getColumnIndex(
                            MediaStore.Images.ImageColumns.DATA)
                    if (index > -1) {
                        data = cursor.getString(index)
                    }
                }
                cursor.close()
            }
        }
        return data
    }
}