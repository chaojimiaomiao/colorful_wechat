package com.rarnu.tophighlight.api

import java.io.Serializable

/**
 * Created by rarnu on 4/20/17.
 */
class Theme : Serializable {

    /**
     * 主题的唯一序列号
     */
    var id = 0
    /**
     * 主题的名称
     */
    var name = ""
    /**
     * 主题作者的唯一序列号
     */
    var author = 0
    /**
     * 主题发布时间
     */
    var publishdate = ""
    /**
     * 主题的描述
     */
    var description = ""
    /**
     * 主题的下载次数
     */
    var downloadcount = 0
    /**
     * 主题的评星数
     */
    var starcount = 0
    /**
     * 当前登录的用户是否已对主题加星，已加星的状态下，可以取消加星
     */
    var stared = 0

    constructor()

    override fun toString(): String {
        return "{id:$id, name:$name, author:$author, publishdate:$publishdate, description:$description, downloadcount:$downloadcount, starcount:$starcount, stared:$stared}"
    }

}