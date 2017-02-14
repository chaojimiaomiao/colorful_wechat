package com.rarnu.tophighlight.api

import java.io.Serializable

/**
 * Created by rarnu on 2/14/17.
 */
object WthApi {

    data class User(
            /**
             * 用户的唯一序列号
             */
            var id: Int,
            /**
             * 用户帐户
             */
            var account: String?,
            /**
             * 用户昵称
             */
            var nickname: String?,
            /**
             * 用户邮箱地址
             */
            var email: String?,
            /**
             * 用户头像的下载地址
             */
            var head: String?,
            /**
             * 用户的自定义备注，签名等
             */
            var comment: String?) : Serializable

    data class Theme(
            /**
             * 主题的唯一序列号
             */
            var id: Int,
            /**
             * 主题的名称
             */
            var name: String?,
            /**
             * 主题作者的唯一序列号
             */
            var author: Int,
            /**
             * 主题发布时间
             */
            var publishdate: String?,
            /**
             * 主题的描述
             */
            var description: String?,
            /**
             * 主题的下载次数
             */
            var downloadcount: Int,
            /**
             * 主题的评星数
             */
            var starcount: Int,
            /**
             * 当前登录的用户是否已对主题加星，已加星的状态下，可以取消加星
             */
            var stared: Boolean) : Serializable

    data class ThemeComment(
            /**
             * 评论的唯一序列号
             */
            var id: Int,
            /**
             * 评论作者的唯一序列号
             */
            var author: Int,
            /**
             * 评论作者的昵称
             */
            var nickname: String?,
            /**
             * 评论的发布时间
             */
            var publishdate: String?,
            /**
             * 评论的内容
             */
            var comment: String?) : Serializable

    /**
     * 新用户注册
     *
     * @param account 用户帐户，帐户必须是唯一的
     * @param password 明文密码，密码将在 JNI 层被加密，并用于真实的请求
     * @param nickname 用户昵称，昵称必须是唯一的
     * @param email 用户的邮件地址，邮件地址必须是唯一的
     *
     * @return 注册结果，成功时返回用户唯一序列号，失败返回 0
     */
    external fun userRegister(account: String?, password: String?, nickname: String?, email: String?): Int

    /**
     * 用户登录
     *
     * @param account 用户帐户
     * @param password 明文密码，密码将在 JNI 层被加密，并用于真实的请求
     *
     * @return 登录结果，成功时返回登录的用户信息，失败返回 null
     */
    external fun userLogin(account: String?, password: String?): User?

    /**
     * 用户验证 email 地址，这个函数用于忘记密码时，通过验证 email 地址来重置密码
     *
     * @param account 用户帐户
     * @param email 用户帐户所对应的邮件地址
     *
     * @return 是否验证成功
     */
    external fun userValidateEmail(account: String?, email: String?): Boolean

    /**
     * 用户忘记密码，在验证后进行重置
     *
     * @param account 用户帐户
     * @param email 用户帐户所对应的邮件地址
     * @param newPassword 新密码（此处为明文密码，密码将在 JNI 层被加密，并用于真实的请求）
     *
     * @return 重置密码是否成功
     */
    external fun userForgetPassword(account: String?, email: String?, newPassword: String?): Boolean

    /**
     * 用户修改密码
     *
     * @param account 用户帐户
     * @param oldPassword 原始密码（此处为明文密码，密码将在 JNI 层被加密，并用于真实的请求）
     * @param newPassword 新密码（此处为明文密码，密码将在 JNI 层被加密，并用于真实的请求）
     *
     * @return 修改密码是否成功
     */
    external fun userChangePassword(account: String?, oldPassword: String?, newPassword: String?): Boolean

    /**
     * 用户修改个人信息
     *
     * @param id 当前登录用户唯一序列号，仅当登录成功后才有
     * @param nickname 用户昵称
     * @param email 用户邮件地址（需注意，若用户忘记了密码又忘记了邮件地址，是无法重置的）
     * @param comment 用户自定义备注信息，签名等
     *
     * @return 是否修改信息成功
     */
    external fun userChangeInfo(id: Int, nickname: String?, email: String?, comment: String?): Boolean

    /**
     * 用户上传头像
     *
     * @param id 当前登录用户唯一序列号，仅当登录成功后才有
     * @param head 头像文件路径，必须是绝对路径
     *
     * @return 是否上传头像成功
     */
    external fun userUploadHead(id: Int, head: String?): Boolean

    /**
     * 获取用户信息
     *
     * @param id 任意用户唯一序列号
     *
     * @return 成功时返回指定的用户信息，失败返回 null
     */
    external fun userGetInfo(id: Int): User?

    /**
     * 获取主题列表
     *
     * @param page 当前页码，页码从 1 开始
     * @param pageSize 每页的主题数
     * @param sort 排序方式，可选 date，download, star，即按发布日期，下载量，星数进行排序，默认可以传空值，空值的情况下按发布日期排序
     *
     * @return 主题列表，失败时返回 null
     */
    external fun themeGetList(page: Int, pageSize: Int, sort: String?): MutableList<Theme>?

    /**
     * 获取指定用户上传的主题列表
     *
     * @param page 当前页码，页码从 1 开始
     * @param pageSize 每页的主题数
     * @param author 主题作者的唯一序列号
     * @param sort 排序方式，可选 date，download, star，即按发布日期，下载量，星数进行排序，默认可以传空值，空值的情况下按发布日期排序
     *
     * @return 主题列表，失败时返回 null
     */
    external fun themeGetListByUser(page: Int, pageSize: Int, author: Int, sort: String?): MutableList<Theme>?

    /**
     * 获取指定的主题信息
     *
     * @param id 主题的唯一序列号
     * @param user 当前登录用户的唯一序列号
     *
     * @return 指定的主题信息，失败时返回 null
     */
    external fun themeGetInfo(id: Int, user: Int): Theme?

    /**
     * 获取指定主题的下载地址
     *
     * @param id 主题的唯一序列号
     *
     * @return 指定主题的下载地址，失败时返回 null 或空字符串
     */
    external fun themeGetDownloadUrl(id: Int): String?

    /**
     * 为指定主题加星
     *
     * @param id 主题的唯一序列号
     * @param user 当前登录用户的唯一序列号
     *
     * @return 是否加星成功
     */
    external fun themeAddStar(id: Int, user: Int): Boolean

    /**
     * 为指定的主题取消加星
     *
     * @param id 主题的唯一序列号
     * @param user 当前登录用户的唯一序列号
     *
     * @return 是否加星成功
     */
    external fun themeRemoveStarme(id: Int, user: Int): Boolean

    /**
     * 发布主题
     *
     * @param author 当前登录用户的唯一序列号
     * @param name 主题的名称，对于同一用户来说，主题的名称是唯一的
     * @param description 主题的描述
     * @param themeFile 主题文件路径，必须是绝对路径
     *
     * @return 发布结果，成功时返回主题唯一序列号，失败时返回 0
     */
    external fun themeUpload(author: Int, name: String?, description: String?, themeFile: String?): Int

    /**
     * 修改主题信息
     *
     * @param id 主题的唯一序列号
     * @param author 当前登录用户的唯一序列号
     * @param name 主题的名称，对于同一用户来说，主题的名称是唯一的
     * @param description 主题的描述
     *
     * @return 是否修改成功
     */
    external fun themeChangeInfo(id: Int, author: Int, name: String?, description: String?): Boolean

    /**
     * 重新上传主题文件
     *
     * @param id 主题的唯一序列号
     * @param author 当前登录用户的唯一序列号
     * @param themeFile 主题文件路径，必须是绝对路径
     *
     * @return 是否上传成功
     */
    external fun themeChangeFile(id: Int, author: Int, themeFile: String?): Boolean

    /**
     * 下架主题
     *
     * @param id 主题的唯一序列号
     * @param author 当前登录用户的唯一序列号
     *
     * @return 是否下架成功
     */
    external fun themeRemove(id: Int, author: Int): Boolean

    /**
     * 获取指定主题下的评论
     *
     * @param id 主题的唯一序列号
     *
     * @return 主题的评论，失败时返回 null
     */
    external fun commentGetList(id: Int): MutableList<ThemeComment>?

    /**
     * 发布评论
     *
     * @param id 主题的唯一序列号
     * @param author 当前登录用户的唯一序列号
     * @param comment 评论内容
     *
     * @return 是否发布评论成功
     */
    external fun commentAdd(id: Int, author: Int, comment: String?): Boolean

    /**
     * 删除评论
     *
     * @param id 评论的唯一序列号
     * @param author 当前登录用户的唯一序列号
     *
     * @return 是否删除评论成功
     */
    external fun commentRemove(id: Int, author: Int): Boolean
}