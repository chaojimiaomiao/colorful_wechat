package com.rarnu.tophighlight.util

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * Created by zhaibingjie on 17/3/19.
 */

object ImageUtil {

    fun saveImage(bmp: Bitmap, name:String) {
        val appDir = File(Environment.getExternalStorageDirectory(), "colorful")
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = name + ".jpg"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bmp.compress(CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun picToDrawables(name:String, nums :Int) : ArrayList<Bitmap> {
        var bitmapList = ArrayList<Bitmap>()
        //分成9格
        val appDir = File(Environment.getExternalStorageDirectory(), "colorful")
        if (!appDir.exists()) {
            return bitmapList
        }
        val fileName = name + ".jpg"
        var bitmapSrc = BitmapFactory.decodeFile(appDir.absolutePath + "/" + fileName)
        var width = bitmapSrc.width
        var height = bitmapSrc.height / nums
        (0..(nums-1)).forEach {
            var bitmap = Bitmap.createBitmap(bitmapSrc, 0, it * height, width, height)
            bitmapList.add(bitmap)
        }
        return bitmapList
    }




}
