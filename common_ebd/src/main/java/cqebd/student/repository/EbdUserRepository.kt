package cqebd.student.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cqebd.student.cache.EbdMemoryCache
import cqebd.student.cache.EbdMemoryCache_Factory
import cqebd.student.network.*
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.User
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
@Singleton
class EbdUserRepository @Inject constructor(
        private val workService: EbdWorkService,
        private val memoryCache: EbdMemoryCache
) {

    fun testCache(){
        println("--->>> cache : $memoryCache")
    }

    fun getUser(account: String, password: String): LiveData<Resource<BaseResponse<User>>> {
        return object : NetworkBoundResource<BaseResponse<User>, BaseResponse<User>>() {
            override fun saveCallResult(item: BaseResponse<User>) {
                println("------ saveCallResult")
                memoryCache.user.value = item
            }

            override fun shouldFetch(data: BaseResponse<User>?): Boolean {
                println("------ shouldFetch")
                println("------ shouldFetch $data")
                return true
            }

            override fun loadFromDb(): LiveData<BaseResponse<User>> {
                println("------ loadFromDb")
                return memoryCache.user
            }

            override fun createCall(): LiveData<BaseResponse<User>> {
                println("------ createCall")
                return workService.userLogin(account, password)
            }

        }.asLiveData()
    }

    fun test(account: String, password: String): Flowable<BaseResponse<User>> {
        return workService.test(account, password)
    }
}