package cqebd.student.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    var user = MutableLiveData<User>()// 用户登陆

    init {
        try {
            user.value = gson.fromJson<User>(cache.getAsString(USER), User::class.java)
        } catch (e: Exception) {
            user.value = null
        }
    }

    fun saveUser(mUser: User) {
        user.value = mUser
        cache.put(USER, gson.toJson(mUser))
    }

    fun getUser(): LiveData<User> {
        return user
    }

}