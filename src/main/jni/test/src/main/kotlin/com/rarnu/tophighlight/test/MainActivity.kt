package com.rarnu.tophighlight.test

import android.app.Activity
import android.os.Bundle
import com.rarnu.tophighlight.api.WthApi
import kotlin.concurrent.thread

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        WthApi.load()

        thread {
            val retRegister = WthApi.userRegister("test003", "123456", "test003", "test003@test.com")
            println("Wth: userRegister => $retRegister")

            val retLogin = WthApi.userLogin("test003", "123456")
            println("Wth: userLogin => $retLogin")
        }

    }
}
