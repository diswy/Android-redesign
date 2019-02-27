package cqebd.student.repository

import androidx.lifecycle.LiveData
import cqebd.student.db.WorkDao
import cqebd.student.network.EbdWorkService
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.VideoInfo
import cqebd.student.vo.WorkInfo
import xiaofu.lib.network.ApiResponse
import xiaofu.lib.network.NetworkBoundResource
import xiaofu.lib.network.NetworkResource
import xiaofu.lib.network.Resource
import xiaofu.lib.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2019/2/21.
 */
@Singleton
class WorkRepository @Inject constructor(
        private val workService: EbdWorkService,
        private val workDao: WorkDao
) {
    private val workRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

//    fun getWorkList(shouldFetch: Boolean = false): LiveData<Resource<List<WorkInfo>>> {
//        return object : NetworkBoundResource<List<WorkInfo>, BaseResponse<List<WorkInfo>>>() {
//            override fun saveCallResult(item: BaseResponse<List<WorkInfo>>) {
//                if (item.data != null) {
//                    println("--->>>插入数据库")
//                    workDao.insertWorkList(item.data)
//                }
//            }
//
//            override fun shouldFetch(data: List<WorkInfo>?): Boolean {
//                println("--->>>是否请求网络：${data.isNullOrEmpty() or shouldFetch or workRateLimit.shouldFetch("work" + 1419)}")
//                return data.isNullOrEmpty() or shouldFetch or workRateLimit.shouldFetch("work" + 1419)
//            }
//
//            override fun loadFromDb(): LiveData<List<WorkInfo>> {
//                println("--->>>加载了数据库")
//                return workDao.loadAllWorkList()
//            }
//
//            override fun createCall(): LiveData<ApiResponse<BaseResponse<List<WorkInfo>>>> {
//                return workService.getWorkList(1419, null, null, 10, 1)
//            }
//
//        }.asLiveData()
//    }

    fun getWorkList(): LiveData<Resource<BaseResponse<List<WorkInfo>>>> {

        return object : NetworkResource<BaseResponse<List<WorkInfo>>>() {

            override fun createCall(): LiveData<ApiResponse<BaseResponse<List<WorkInfo>>>> {
                return workService.getWorkList(1419, null, null, 10, 1)
            }

            override fun saveResult(item: BaseResponse<List<WorkInfo>>) {

            }

        }.asLiveData()
    }

}