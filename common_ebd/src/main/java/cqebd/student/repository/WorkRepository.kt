package cqebd.student.repository

import androidx.lifecycle.LiveData
import cqebd.student.db.WorkDao
import cqebd.student.network.EbdWorkService
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.WorkInfo
import xiaofu.lib.network.ApiResponse
import xiaofu.lib.network.NetworkResource
import xiaofu.lib.network.Resource
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

    fun getWorkList(): LiveData<Resource<BaseResponse<List<WorkInfo>>>> {

        return object : NetworkResource<BaseResponse<List<WorkInfo>>>() {

            override fun createCall(): LiveData<ApiResponse<BaseResponse<List<WorkInfo>>>> {
                return workService.getWorkList(1419, null, null, 10, 1)
            }

        }.asLiveData()
    }

}