package com.rarnu.tophighlight

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.rarnu.tophighlight.api.LocalApi
import com.rarnu.tophighlight.api.WthApi
import com.rarnu.tophighlight.market.LocalTheme
import com.rarnu.tophighlight.market.UserLoginRegisterActivity
import com.rarnu.tophighlight.ui.CustomToolBar
import com.rarnu.tophighlight.util.UIUtils
import java.io.File




class PublishActivity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_CODE_CHOOSE_IMAGE = 100;
    private val REQUEST_CODE_CROP_IMAGE = 101
    private val CHOOSE_BIG_PICTURE = 1
    override fun onClick(v: View?) {

    }

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

        val IMAGE_FILE_LOCATION = "file:///sdcard/temp.jpg"//temp file
        val imageUri = Uri.parse(IMAGE_FILE_LOCATION)//The Uri to store the big bitmap

        findViewById(R.id.publish_back).setOnClickListener {
            openGallery()
        }

        findViewById(R.id.publish_topback).setOnClickListener {
        }

        findViewById(R.id.publish_bottomback).setOnClickListener {
        }

    }

    private fun loadTheme() {
        ini = LocalTheme.themeFlower
        var themePreview = findViewById(R.id.vPreview) as ThemePreviewView
        themePreview.setThemePreview(ini)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, null)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE)
    }

    /**
     * 裁减图片操作
     * @param uri
     */
    private fun startCropImage(uri: Uri) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        // 使图片处于可裁剪状态
        intent.putExtra("crop", "true")
        // 裁剪框的比例（根据需要显示的图片比例进行设置）
        intent.putExtra("aspectX", 2)
        intent.putExtra("aspectY", 3)
        // 让裁剪框支持缩放
        intent.putExtra("scale", true)
        // 裁剪后图片的大小（注意和上面的裁剪比例保持一致）
        intent.putExtra("outputX", UIUtils.dip2px(480))
        intent.putExtra("outputY", UIUtils.dip2px(720))
        // 传递原图路径
        val cropFile = File(Environment.getExternalStorageDirectory().toString() + "crop_image.jpg")
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
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (intent != null) {
            when (requestCode) {
            // 将选择的图片进行裁剪
                REQUEST_CODE_CHOOSE_IMAGE -> if (intent.data != null) {
                    var iconUri = intent.data
                    startCropImage(iconUri)
                }
            // 将裁剪后的图片进行上传
                REQUEST_CODE_CROP_IMAGE -> {
                    var ret = WthApi.themeUpload(LocalApi.userId, "name", "what", ini?.listPath?.replace(".png", ".ini"))
                    Log.e("", "ret" +ret)
                    //
                }
                else -> {
                }
            }// 上传图片操作
        }
    }
}
