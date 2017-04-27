package com.rarnu.tophighlight.api

/**
 * Created by rarnu on 4/20/17.
 */
class User {

    /**
     * 用户的唯一序列号
     */
    var id = 0
    /**
     * 用户帐户
     */
    var account = ""
    /**
     * 用户昵称
     */
    var nickname = ""
    /**
     * 用户邮箱地址
     */
    var email = ""
    /**
     * 用户头像的下载地址
     */
    var head = ""
    /**
     * 用户的自定义备注，签名等
     */
    var comment = ""

    constructor()

    constructor(id: Int,
                account: String,
                nickname: String,
                email: String,
                head: String,
                comment: String) {
        this.id = id
        this.account = account
        this.nickname = nickname
        this.email = email
        this.head = head
        this.comment = comment
    }
}