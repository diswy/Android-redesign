package cqebd.student.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import cqebd.student.cache.EbdMemoryCache
import cqebd.student.network.EbdWorkService
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.User
import xiaofu.lib.network.ApiResponse
import xiaofu.lib.network.NetworkBoundResource
import xiaofu.lib.network.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
@Singleton
class EbdUserRepository @Inject constructor(
        private val workService: EbdWorkService,
        private val memoryCache: EbdMemoryCache,
        private val app: Application
) {


    fun getUser(account: String, password: String, shouldFetch: Boolean = false): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, BaseResponse<User>>() {
            override fun saveCallResult(item: BaseResponse<User>) {
                if (item.isSuccess && item.data != null) {
                    memoryCache.saveUser(item.data)
                } else {
                    Toast.makeText(app, item.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun shouldFetch(data: User?): Boolean {
                return (data == null) or shouldFetch// 当缓存为null或强制刷新时才请求网络
            }

            override fun loadFromDb(): LiveData<User> {
                return memoryCache.getUser()
            }

            override fun createCall(): LiveData<ApiResponse<BaseResponse<User>>> {
                return workService.userLogin(account, password)
            }

        }.asLiveData()
    }

}