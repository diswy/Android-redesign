package cqebd.student.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 单例模式
 * 内存缓存以及SharePreference
 * 请在Application中初始化
 * Created by @author xiaofu on 2018/12/18.
 */
@Singleton
class EbdMemoryCache @Inject constructor() {

    var user = MutableLiveData<BaseResponse<User>>()

//    fun setUser(data :User){
//        user.value =    data
//    }

//    private var user: User? = null
//
//    private object Instance {
//        val instance = EbdMemoryCache()
//    }
//
//    companion object {
//        val INSTANCE = Instance.instance
//    }

//    fun setUser(mUser: User) {
//        this.user = mUser
//    }
//
//    fun getUser(): User? {
//        return user
//    }


}