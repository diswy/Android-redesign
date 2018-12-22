package cqebd.student.cache

import androidx.lifecycle.MutableLiveData
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
class EbdMemoryCache @Inject constructor(val cache: ACache) {
    var user = MutableLiveData<User>()

    init {
        user.value = null
    }

}