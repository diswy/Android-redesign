package cqebd.student.cache

import com.google.gson.Gson
import cqebd.student.vo.User
import xiaofu.lib.cache.ACache
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 单例模式
 * 内存缓存以及SharePreference
 * Created by @author xiaofu on 2018/12/18.
 */
@Singleton
class EbdMemoryCache @Inject constructor(private val cache: ACache, private val gson: Gson) {

    // 快捷获取用户ID
    fun getId(): Int {
        return try {
            val user = gson.fromJson<User>(cache.getAsString(USER), User::class.java)
            user.ID
        } catch (e: Exception) {
            -1
        }
    }

    fun saveUser(mUser: User) {
        cache.put(USER, gson.toJson(mUser))
    }

}