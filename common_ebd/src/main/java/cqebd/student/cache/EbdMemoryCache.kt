package cqebd.student.cache

import android.content.Context
import cqebd.student.vo.User

/**
 * 单例模式
 * 内存缓存以及SharePreference
 * 请在Application中初始化
 * Created by @author xiaofu on 2018/12/18.
 */
class EbdMemoryCache private constructor() {

    private var user: User? = null

    private object Instance {
        val instance = EbdMemoryCache()
    }

    companion object {
        val INSTANCE = Instance.instance
    }

    fun setUser(mUser: User) {
        this.user = mUser
    }

    fun getUser(): User? {
        return user
    }


}