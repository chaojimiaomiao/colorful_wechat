package com.rarnu.tophighlight.market

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import com.rarnu.tophighlight.R
import com.rarnu.tophighlight.api.LocalApi
import com.rarnu.tophighlight.api.Theme
import com.rarnu.tophighlight.api.WthApi
import java.io.*
import java.net.MalformedURLException
import java.net.URL
import kotlin.concurrent.thread


/**
 * getThemeList page=1 pageSize=2 sort=date
 * theme_get_download_url id=15
 * errorcode url
 * http://rarnu.xyz/wth/theme/url
 */
class ThemeListActivity : BaseMarkerActivity() {

    companion object {
        val MENUID_PROFILE = 1;
        val BASEURL = "http://rarnu.xyz/wth/theme/";
    }

    private var miProfile: MenuItem? = null
    private var listTheme: MutableList<WthApi.ThemeINI>? = null
    private var gvTheme: GridView? = null
    private var adapterTheme: ThemeListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalApi.ctx = this
        setContentView(R.layout.activity_themelist)
        actionBar.setTitle(R.string.view_themes)
        gvTheme = findViewById(R.id.gvTheme) as GridView?

        listTheme = arrayListOf()
        adapterTheme = ThemeListAdapter(this, listTheme)
        gvTheme?.adapter = adapterTheme

        loadThemeList()
    }

    private fun loadLocalThemeList() {
        //listTheme?.add(LocalTheme.themePurple)
        //adapterTheme?.setList(listTheme)
    }

    private fun loadThemeList() {
        val hTheme = object : Handler() {
            override fun handleMessage(msg: Message?) {
                adapterTheme?.setList(listTheme)
                super.handleMessage(msg)
            }
        }


        var wht = Environment.getExternalStorageDirectory().absolutePath
        Log.e("wht", "wht: " + wht)
        thread {
            listTheme?.clear()
            val list = WthApi.themeGetList(1, 20, "date") as List<Theme>
            println("list = $list")
            for(theme : Theme in list) {
                Log.e("wht", "theme => $theme")
                var url = WthApi.themeGetDownloadUrl(theme.id)
                var wht = Environment.getExternalStorageDirectory().absolutePath
                Log.e("wht", "wht: " + wht)

                //val ini = WthApi.readThemeFromINI(wht + "/theme.ini")

                // TODO: val filename = download(BASEURL + url)
                // Log.d("themelist", "filename: " + filename)
                /*if (ini != null) {
                    listTheme?.add(ini)
                }*/
            }
            // TODO: download theme file and make preview
            hTheme.sendEmptyMessage(0)
        }
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

    @Throws(Exception::class)
    fun download(docUrl: String): String {
        var fileName = "/sdcard/" + docUrl +  ".ini"
        val file = File(fileName)
        if (file.exists()) {    //如果目标文件已经存在
            file.delete()    //则删除旧文件
        }
        //1K的数据缓冲
        val bs = ByteArray(1024)
        //读取到的数据长度
        var len: Int
        try {
            val url = URL(docUrl)
            //获取链接
            //URLConnection conn = url.openConnection();
            //创建输入流
            val input = url.openStream() as InputStream
            //获取文件的长度
            //int contextLength = conn.getContentLength();
            //输出的文件流
            val os = FileOutputStream(file)
            //开始读取
            var len = input.read(bs)
            while (len != -1) {
                os.write(bs, 0, len)
            }
            //完毕关闭所有连接
            os.close()
            input.close()
        } catch (e: MalformedURLException) {
            println("创建URL对象失败")
            throw e
        } catch (e: FileNotFoundException) {
            println("无法加载文件")
            throw e
        } catch (e: IOException) {
            println("获取连接失败")
            throw e
        }
        return fileName
    }
}