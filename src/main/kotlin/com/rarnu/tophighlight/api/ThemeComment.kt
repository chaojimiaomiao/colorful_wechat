package com.rarnu.tophighlight.api

import java.io.Serializable

/**
 * Created by rarnu on 4/20/17.
 */
class ThemeComment: Serializable {

    /**
     * 评论的唯一序列号
     */
    var id = 0
    /**
     * 评论作者的唯一序列号
     */
    var author = 0
    /**
     * 评论作者的昵称
     */
    var nickname = ""
    /**
     * 评论的发布时间
     */
    var publishdate = ""
    /**
     * 评论的内容
     */
    var comment = ""

    constructor()

    constructor(
                id: Int,
                author: Int,
                nickname: String,
                publishdate: String,
                comment: String) {
        this.id = id
        this.author = author
        this.nickname = nickname
        this.publishdate = publishdate
        this.comment = comment
    }

}