package com.rarnu.tophighlight.util

import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * Created by rarnu on 1/29/17.
 */
class BuildProperties {

    private var properties: Properties? = null

    constructor() {
        properties = Properties()
        properties?.load(FileInputStream(File(Environment.getRootDirectory(), "build.prop")))
    }

    fun containsKey(key: Any?): Boolean = properties!!.containsKey(key)
    fun containsValue(value: Any?): Boolean = properties!!.containsValue(value)
    fun entrySet(): MutableSet<MutableMap.MutableEntry<Any?, Any?>>? = properties!!.entries
    fun getProperty(name: String?): String? = properties!!.getProperty(name)
    fun getProperty(name: String?, defaultValue: String?): String? = properties!!.getProperty(name, defaultValue)
    fun isEmpty(): Boolean = properties!!.isEmpty
    fun keys(): Enumeration<Any?>? = properties!!.keys()
    fun keySet(): MutableSet<Any?> = properties!!.keys
    fun size(): Int = properties!!.size
    fun values(): MutableCollection<Any?>? = properties!!.values

    companion object {
        fun newInstance(): BuildProperties = BuildProperties()
    }
}