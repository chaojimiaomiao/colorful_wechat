package com.rarnu.tophighlight.diy

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.rarnu.tophighlight.LogoutActivity
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.api.LocalApi
import com.rarnu.tophighlight.api.ThemeINI
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.market.UserLoginRegisterActivity
import com.rarnu.tophighlight.ui.CustomToolBar
import com.rarnu.tophighlight.util.Constants.REQUEST_CODE_LOGIN
import com.rarnu.tophighlight.util.UIUtils
import com.rarnu.tophighlight.xposed.XpConfig
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class PublishActivity : AppCompatActivity() {

    private val REQUEST_CODE_CHOOSE_LISTBAC = 1
    private val REQUEST_CODE_CHOOSE_TOPBAC = 2
    private val REQUEST_CODE_CHOOSE_BOTTOMBAC = 3
    private val REQUEST_CODE_CROP_LISTBAC = 101
    private val REQUEST_CODE_CROP_TOPBAC = 102
    private val REQUEST_CODE_CROP_BOTTOMBAC = 103
    private val REQUEST_CODE_PAY = 1001

    private var ini : ThemeINI?= null

    private var bottomLayout : View?=null
    private var toolBar : CustomToolBar?=null
    private var rootView : View?= null
    private var inputName : EditText?= null
    private var inputDesc : EditText?= null
    private var uploadMarket : CheckBox?= null
    var listbackPath = ""
    var topbackPath = ""
    var bottombackPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)

        rootView = findViewById(R.id.publish_act)
        toolBar = findViewById(R.id.publish_toolbar) as CustomToolBar?
        setSupportActionBar(toolBar)
        setTitle(R.string.diy_theme)
        /*if(getSupportActionBar() != null)
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)*/

        bottomLayout = findViewById(R.id.publish_bottom)
        inputName = findViewById(R.id.publish_input_name) as EditText?
        inputDesc = findViewById(R.id.publish_input_desc) as EditText?
        uploadMarket = findViewById(R.id.publish_market) as CheckBox

        if (LocalApi.userId != 0)
            (findViewById(R.id.publish_login_btn) as Button).text = LocalApi.curUser?.account

        findViewById(R.id.publish_login_btn).setOnClickListener {
            if (LocalApi.userId == 0)
                startActivityForResult(Intent(this, UserLoginRegisterActivity::class.java), REQUEST_CODE_LOGIN)
            else
                startActivity(Intent(this, LogoutActivity::class.java))
        }

        findViewById(R.id.btn_publish).setOnClickListener {
            if (inputName?.editableText.toString().isBlank()) {
                Toast.makeText(this, R.string.no_theme_name, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (inputDesc?.editableText.toString().isBlank()) {
                Toast.makeText(this, R.string.no_theme_desc, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (listbackPath.equals("")) {
                Toast.makeText(this, R.string.upload_listback, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (topbackPath.equals("")) {
                Toast.makeText(this, R.string.upload_topback, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (bottombackPath.equals("")) {
                Toast.makeText(this, R.string.upload_bottomback, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (LocalApi.userId == 0) {
                Toast.makeText(this, "请先登录!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, UserLoginRegisterActivity::class.java))
                return@setOnClickListener
            }
            generateThemeFile()

            if (uploadMarket!!.isChecked) {
                //这里要先付钱
                var intent = Intent(this, PayActivity::class.java)
                intent.putExtra("much", 5.0)
                startActivityForResult(intent, REQUEST_CODE_PAY)
            } else {
                var intent = Intent(this, PayActivity::class.java)
                intent.putExtra("much", 2.0)
                startActivityForResult(intent, REQUEST_CODE_PAY)
            }

        }

        findViewById(R.id.publish_back).setOnClickListener {
            openGallery(REQUEST_CODE_CHOOSE_LISTBAC)
        }

        findViewById(R.id.publish_topback).setOnClickListener {
            openGallery(REQUEST_CODE_CHOOSE_TOPBAC)
        }

        findViewById(R.id.publish_bottomback).setOnClickListener {
            openGallery(REQUEST_CODE_CHOOSE_BOTTOMBAC)
        }

    }

    private fun openGallery(pos: Int) {
        val intent = Intent(Intent.ACTION_PICK, null)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(intent, pos)
    }

    /**
     * 裁减图片操作
     * @param uri
     */
    private fun startCropImage(uri: Uri, xAspect: Int, yAspect: Int, reqCode : Int) : String {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        // 使图片处于可裁剪状态
        intent.putExtra("crop", "true")
        // 裁剪框的比例（根据需要显示的图片比例进行设置）
        intent.putExtra("aspectX", xAspect)
        intent.putExtra("aspectY", yAspect)
        // 让裁剪框支持缩放
        intent.putExtra("scale", true)
        // 裁剪后图片的大小（注意和上面的裁剪比例保持一致）
        intent.putExtra("outputX", UIUtils.dip2px(480))
        intent.putExtra("outputY", UIUtils.dip2px(720))
        // 传递原图路径
        val cropFile = File(XpConfig.BASE_FILE_PATH + "/crop_" + xAspect + yAspect + ".jpg")
        var cropImageUri = Uri.fromFile(cropFile)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri)
        // 设置裁剪区域的形状，默认为矩形，也可设置为原形
        //intent.putExtra("circleCrop", true);
        // 设置图片的输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
        // return-data=true传递的为缩略图，小米手机默认传递大图，所以会导致onActivityResult调用失败
        intent.putExtra("return-data", false)
        // 是否需要人脸识别
        intent.putExtra("noFaceDetection", true)
        startActivityForResult(intent, reqCode)

        return cropFile.absolutePath
    }

    fun getBmpFromPath(path : String) : Drawable? {
        try {
            var stream = FileInputStream(path)
            var bmp = BitmapFactory.decodeStream(stream)
            return BitmapDrawable(bmp)
        } catch (ex : FileNotFoundException) {
            Log.e("rarnu", "FileNotFoundException")
            return null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                if (intent.data != null) {
                    var iconUri = intent.data
                    when (requestCode) {
                        REQUEST_CODE_CHOOSE_LISTBAC -> listbackPath = startCropImage(iconUri, 2, 3, REQUEST_CODE_CROP_LISTBAC)
                        REQUEST_CODE_CHOOSE_TOPBAC -> topbackPath = startCropImage(iconUri, 9, 1, REQUEST_CODE_CROP_TOPBAC)
                        REQUEST_CODE_CHOOSE_BOTTOMBAC -> bottombackPath = startCropImage(iconUri, 8, 1, REQUEST_CODE_CROP_BOTTOMBAC)
                    }
                }

                when (requestCode) {
                    REQUEST_CODE_CROP_LISTBAC -> rootView?.background = getBmpFromPath(listbackPath)
                    REQUEST_CODE_CROP_TOPBAC -> toolBar?.background = getBmpFromPath(topbackPath)
                    REQUEST_CODE_CROP_BOTTOMBAC -> bottomLayout?.background = getBmpFromPath(bottombackPath)
                }

                if (listbackPath.isNotBlank() && bottombackPath.isNotBlank() && topbackPath.isNotBlank() && inputName?.editableText.toString().isNotBlank()) {
                    findViewById(R.id.btn_publish).setBackgroundDrawable(resources.getDrawable(R.drawable.bg_button_or_radius))
                }
            } else {

                when(requestCode) {
                    REQUEST_CODE_LOGIN -> (findViewById(R.id.publish_login_btn) as Button).text = LocalApi.curUser?.account
                    REQUEST_CODE_PAY -> {
                        var ret = WthApi.themeUpload(LocalApi.userId, ini?.themeName, inputDesc?.editableText.toString(), XpConfig.BASE_FILE_PATH + "/colorful/" + ini?.themeName + ".ini")
                        Log.e("", "发布结果" + ret)
                        if (ret != 0) {
                            Toast.makeText(this, "发布成功!SUCCESS", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "发布失败!FAILURE", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


            //rootView?.background = BitmapDrawable(startCropImage(iconUri, 2, 3))
            //toolBar?.setBackDrawable(BitmapDrawable(startCropImage(iconUri, 9, 1)))
            //bottomLayout?.background = BitmapDrawable(startCropImage(iconUri, 8, 1))
            // 将裁剪后的图片进行上传
            /*REQUEST_CODE_CROP_IMAGE -> {
                var ret = WthApi.themeUpload(LocalApi.userId, "name", "what", ini?.listPath?.replace(".png", ".ini"))
                Log.e("", "ret" +ret)
                //
            }
            else -> {
            }*/
        }
    }

    fun generateThemeFile() {
        var theme = ThemeINI()
        theme.themeName = inputName?.editableText.toString()
        theme.listPath = listbackPath
        theme.bottomBarPath = bottombackPath
        theme.statusBarPath = topbackPath
        if (WthApi.writeThemeToINI(XpConfig.BASE_FILE_PATH + "/colorful/" + theme.themeName + ".ini", theme)) ini = theme
    }
}
