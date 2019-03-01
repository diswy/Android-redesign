package cqebd.student.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import cqebd.student.repository.WorkRepository
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.WorkInfo
import xiaofu.lib.network.Resource
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2019/2/21.
 */
class WorkViewModel @Inject constructor(private val workRepository: WorkRepository) : ViewModel() {

    val workList = MediatorLiveData<Resource<BaseResponse<List<WorkInfo>>>>()

    /**
     * 学生作业列表
     */
    fun getWorkList() {
        workList.addSource(workRepository.getWorkList()) {
            workList.removeSource(workRepository.getWorkList())
            workList.value = it
        }
    }

}