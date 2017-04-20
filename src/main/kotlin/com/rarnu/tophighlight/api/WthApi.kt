package com.rarnu.tophighlight.api

import android.util.Log
import com.rarnu.tophighlight.xposed.Versions
import java.io.Serializable

/**
 * Created by rarnu on 2/14/17.
 *
 * 全部的 API 在底层均是同步实现，因此在上层调用时，需要异步调用
 *
 * 例如: thread { ... }
 */
object WthApi {

    /**
     * 加载 API 库，由于 Xposed 跨进程的原因，不能直接使用 init 进行加载，因此单独提供此加载功能，供需要时使用
     */
    fun load() {
        System.loadLibrary("wthapi")
    }

    fun load(path: String) {
        try {
            System.load(path)
            Log.e("XposedModule", "jni library loaded")
        } catch (th: Throwable) {
            Log.e("XposedModule", "load jni library error => $th")
        }
    }

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

    data class ThemeINI(
            //新增的
            var themeId: Int,
            var themeName: String?,
            var type: Int,
            var normalColor: Int,
            var normalPressColor: Int,
            var statusBarPath: String,
            var bottomBarPath: String,
            var listPath: String,

            //原有的
            var statusBarColor: Int,
            var showDivider: Boolean,
            var dividerColor: Int,
            var darkerStatusBar: Boolean,
            var darkStatusBarText: Boolean,
            var macColor: Int,
            var macPressColor: Int,
            var readerColor: Int,
            var readerPressColor: Int,
            var bottomBarColor: Int,
            var topColors0: Int,
            var topColors1: Int,
            var topColors2: Int,
            var topColors3: Int,
            var topColors4: Int,
            var topColors5: Int,
            var topColors6: Int,
            var topColors7: Int,
            var topColors8: Int,
            var topColors9: Int,
            var topPressColors0: Int,
            var topPressColors1: Int,
            var topPressColors2: Int,
            var topPressColors3: Int,
            var topPressColors4: Int,
            var topPressColors5: Int,
            var topPressColors6: Int,
            var topPressColors7: Int,
            var topPressColors8: Int,
            var topPressColors9: Int
    ) : Serializable

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
    external fun themeGetList(page: Int, pageSize: Int, sort: String?): List<Theme>?

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
    external fun themeGetListByUser(page: Int, pageSize: Int, author: Int, sort: String?): List<Theme>?

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
    external fun themeRemoveStar(id: Int, user: Int): Boolean

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
    external fun commentGetList(id: Int): List<ThemeComment>?

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

    /**
     * 将主题文件转化为类格式
     *
     * @param themeFile 主题文件路径
     *
     * @return 主题类，转化失败返回 null
     */
    external fun readThemeFromINI(themeFile: String?): ThemeINI?

    /**
     * 将主题类保存至主题文件
     *
     * @param themeFile 要保存到的文件路径
     * @param theme 主题类
     *
     * @return 是否保存成功
     */
    external fun writeThemeToINI(themeFile: String?, theme: ThemeINI?): Boolean

    /**
     * 记录设备信息，仅当程序启动时调用一次
     */
    external fun recordDevice()

    /**
     * 发送反馈信息
     *
     * @param appVer 当前软件版本，从 AndroidManifest.xml 获取
     * @param userId 已登录用户的唯一序列号，未登录时传0
     * @param nickname 用户昵称，仅当未登录时有效，已登录时传""
     * @param email 邮箱地址，仅当未登录时有效，已登录时传""
     * @param content 反馈内容，不能为空
     * @param img1
     * @param img2
     * @param img3 附加的图片，若是有图则传图片的绝对路径，若是没有则传""，最多三张图
     *
     * @return 是否发送成功
     */
    external fun feedbackAdd(appVer: Int, userId: Int, nickname: String?, email: String?, content: String?, img1: String?, img2: String?, img3: String?): Boolean

    /**
     * 检查 Xposed 是否已安装
     *
     * @return 是否已安装
     */
    external fun xposedInstalled(): Boolean

    /**
     * 检查是否有适用于当前版本微信的配置文件，若没有则进行下载
     *
     * @param 当前安装的微信版本号
     * @return 已有配置文件或下载成功，返回 true，否则返回 false
     *
     */
    external fun checkAndDownloadVersion(AVer: String?): Boolean

    /**
     * 加载指定微信的配置文件
     *
     * @param 当前安装的微信版本号
     * @return 是否加载成功，若加载成功，Versions.kt 内指定变量将拥有特定值
     */
    external fun loadVersion(AVer: String?): Versions?

}