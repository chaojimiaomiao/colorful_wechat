package com.rarnu.tophighlight.util

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.util.Log
import com.rarnu.tophighlight.xposed.XpConfig.BASE_FILE_PATH
import java.io.File
import java.io.FileOutputStream

/**
 * Created by zhaibingjie on 17/3/19.
 */

object ImageUtil {

    fun saveImagePrivate(bmp: Bitmap, name:String, activity: Activity) :Boolean {
        val appDir = File(BASE_FILE_PATH, "colorful")
        return saveImage(bmp, name, appDir)
    }

    fun saveImage(bmp: Bitmap, name:String, appDir :File) :Boolean {
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = name + ".png"
        val file = File(appDir, fileName)
        if (file.exists()) return false
        try {
            val fos = FileOutputStream(file)
            bmp.compress(CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            Log.e("ImageUtil", "Exception => $e")
        }
        return true
    }

    /*
    fun picToDrawables(name:String, nums :Int) : ArrayList<Bitmap> {
        var bitmapList = ArrayList<Bitmap>()
        //分成9格
        val appDir = File(Environment.getExternalStorageDirectory(), "colorful")
        if (!appDir.exists()) {
            return bitmapList
        }
        val fileName = name + ".png"
        var bitmapSrc = BitmapFactory.decodeFile(appDir.absolutePath + "/" + fileName)
        var width = bitmapSrc.width
        var height = bitmapSrc.height / nums
        (0..(nums-1)).forEach {
            var bitmap = Bitmap.createBitmap(bitmapSrc, 0, it * height, width, height)
            bitmapList.add(bitmap)
        }
        return bitmapList
    }
    */

}
