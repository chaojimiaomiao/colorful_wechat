package com.rarnu.tophighlight

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import c.b.BP
import c.b.PListener
import c.b.QListener
import java.io.File
import java.io.FileOutputStream

class PayActivity : Activity() , RadioGroup.OnCheckedChangeListener {

    var APPID = "33c823eea00877758bef540652912226"
    // 此为微信支付插件的官方最新版本号,请在更新时留意更新说明
    var PLUGINVERSION = 7

    var go: Button? = null
    var type: RadioGroup? = null
    var dialog: ProgressDialog? = null
    var much = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        BP.init(APPID)

        much = 0.1//intent.getDoubleExtra("much", 1.0)
        go = findViewById(R.id.go) as Button
        type = findViewById(R.id.type) as RadioGroup

        go?.text = "确认支付 "+ much + " 元";
        go!!.setOnClickListener(View.OnClickListener {
            if (type!!.getCheckedRadioButtonId() == R.id.alipay)
                pay(true)
            else if (type!!.getCheckedRadioButtonId() == R.id.wxpay)
                pay(false)
            else if (type!!.getCheckedRadioButtonId() == R.id.query)
                query()
        })
    }

    /**
     * 检查某包名应用是否已经安装

     * @param packageName 包名
     * *
     * @param browserUrl  如果没有应用市场，去官网下载
     * *
     * @return
     */
    private fun checkPackageInstalled(packageName: String, browserUrl: String): Boolean {
        try {
            packageManager.getPackageInfo(packageName, 0)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("market://details?id=" + packageName)
                startActivity(intent)
            } catch (ee: Exception) {// 连应用市场都没有，用浏览器去支付宝官网下载
                try {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(browserUrl)
                    startActivity(intent)
                } catch (eee: Exception) {
                    Toast.makeText(this@PayActivity,
                            "您的手机上没有没有应用市场也没有浏览器，我也是醉了，你去想办法安装支付宝/微信吧",
                            Toast.LENGTH_SHORT).show()
                }

            }

        }

        return false
    }

    private val REQUESTPERMISSION = 101

    private fun installApk(s: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUESTPERMISSION)
        } else {
            installBmobPayPlugin(s)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUESTPERMISSION) {
            if (permissions[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    installBmobPayPlugin("bp.db")
                } else {
                    Toast.makeText(this@PayActivity, "您拒绝了权限，这样无法安装支付插件", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /**
     * 调用支付

     * @param alipayOrWechatPay 支付类型，true为支付宝支付,false为微信支付
     */
    fun pay(alipayOrWechatPay: Boolean) {
        if (alipayOrWechatPay) {
            if (!checkPackageInstalled("com.eg.android.AlipayGphone",
                    "https://www.alipay.com")) { // 支付宝支付要求用户已经安装支付宝客户端
                Toast.makeText(this@PayActivity, "请安装支付宝客户端", Toast.LENGTH_SHORT)
                        .show()
                return
            }
        } else {
            if (checkPackageInstalled("com.tencent.mm", "http://weixin.qq.com")) {// 需要用微信支付时，要安装微信客户端，然后需要插件
                // 有微信客户端，看看有无微信支付插件
                val pluginVersion = BP.getPluginVersion(this)
                if (pluginVersion < PLUGINVERSION) {// 为0说明未安装支付插件,
                    // 否则就是支付插件的版本低于官方最新版
                    Toast.makeText(
                            this@PayActivity,
                            if (pluginVersion == 0)
                                "监测到本机尚未安装支付插件,无法进行支付,请先安装插件(无流量消耗)"
                            else
                                "监测到本机的支付插件不是最新版,最好进行更新,请先更新插件(无流量消耗)",
                            Toast.LENGTH_SHORT).show()
                    //                    installBmobPayPlugin("bp.db");

                    installApk("bp.db")
                    return
                }
            } else {// 没有安装微信
                Toast.makeText(this@PayActivity, "请安装微信客户端", Toast.LENGTH_SHORT).show()
                return
            }
        }

        showDialog("正在获取订单...\nSDK版本号:" + BP.getPaySdkVersion())
        val name = getName()

        try {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            val cn = ComponentName("com.bmob.app.sport",
                    "com.bmob.app.sport.wxapi.BmobActivity")
            intent.component = cn
            this.startActivity(intent)
        } catch (e: Throwable) {
            e.printStackTrace()
        }

        BP.pay(name, getBody(), getPrice(), alipayOrWechatPay, object : PListener {

            // 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
            override fun unknow() {
                Toast.makeText(this@PayActivity, "支付结果未知,请稍后手动查询", Toast.LENGTH_SHORT)
                        .show()
                hideDialog()
            }

            // 支付成功,如果金额较大请手动查询确认
            override fun succeed() {
                Toast.makeText(this@PayActivity, "支付成功!", Toast.LENGTH_SHORT).show()
                hideDialog()
                setResult(RESULT_OK)
                finish()
            }

            // 无论成功与否,返回订单号
            override fun orderId(orderId: String) {
                showDialog("获取订单成功!请等待跳转到支付页面~")
            }

            // 支付失败,原因可能是用户中断支付操作,也可能是网络原因
            override fun fail(code: Int, reason: String) {

                // 当code为-2,意味着用户中断了操作
                // code为-3意味着没有安装BmobPlugin插件
                if (code == -3) {
                    Toast.makeText(
                            this@PayActivity,
                            "监测到你尚未安装支付插件,无法进行支付,请先安装插件(已打包在本地,无流量消耗),安装结束后重新支付",
                            Toast.LENGTH_SHORT).show()
                    //                    installBmobPayPlugin("bp.db");
                    installApk("bp.db")
                } else {
                    Toast.makeText(this@PayActivity, "支付中断!", Toast.LENGTH_SHORT)
                            .show()
                    Toast.makeText(
                            this@PayActivity,
                            name + "'s pay status is fail, error code is \n"
                                    + code + " ,reason is " + reason,
                            Toast.LENGTH_LONG).show()
                }
                hideDialog()
            }
        })
    }

    // 执行订单查询
    fun query() {
        showDialog("正在查询订单...")
        val orderId = getOrder()

        BP.query(orderId, object : QListener {

            override fun succeed(status: String) {
                Toast.makeText(this@PayActivity, "查询成功!该订单状态为 : " + status,
                        Toast.LENGTH_SHORT).show()
                hideDialog()
            }

            override fun fail(code: Int, reason: String) {
                Toast.makeText(this@PayActivity, "查询失败", Toast.LENGTH_SHORT).show()
                hideDialog()
            }
        })
    }

    // 以下仅为控件操作，可以略过
    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.alipay -> {
                go!!.setText("支付宝支付")
            }
            R.id.wxpay -> {
                go!!.setText("微信支付")
            }
            R.id.query -> {
                go!!.setText("订单查询")
            }

            else -> {
            }
        }
    }

    fun getPrice(): Double {
        return much
    }

    fun getName(): String {
        return resources.getString(R.string.goods_name)
    }

    // 商品详情(可不填)
    fun getBody(): String {
        return ""
    }

    // 支付订单号(查询时必填)
    fun getOrder(): String {
        return ""
    }

    fun showDialog(message: String) {
        if (dialog == null) {
            dialog = ProgressDialog(this)
            dialog!!.setCancelable(true)
        }
        dialog!!.setMessage(message)
        dialog!!.show()
    }

    fun hideDialog() {
        if (dialog != null && dialog!!.isShowing()) {
            dialog!!.dismiss()
        }
    }

    /**
     * 安装assets里的apk文件

     * @param fileName
     */
    fun installBmobPayPlugin(fileName: String) {
        try {
            val input = assets.open(fileName)
            val file = File(Environment.getExternalStorageDirectory().toString()
                    + File.separator + fileName + ".apk")
            if (file.exists())
                file.delete()
            file.createNewFile()
            val fos = FileOutputStream(file)
            val temp = ByteArray(1024)
            var i = input.read(temp)
            while (i > 0) {
                fos.write(temp, 0, i)
                i = input.read(temp)
            }
            fos.close()
            input.close()

            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setDataAndType(Uri.parse("file://" + file),
                    "application/vnd.android.package-archive")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
