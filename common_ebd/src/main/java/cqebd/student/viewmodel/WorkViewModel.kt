package cqebd.student.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import cqebd.student.repository.WorkRepository
import cqebd.student.vo.WorkInfo
import xiaofu.lib.network.Resource
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2019/2/21.
 */
class WorkViewModel @Inject constructor(private val workRepository: WorkRepository) : ViewModel() {

    val workList = MediatorLiveData<Resource<List<WorkInfo>>>()

//    fun getWorkList(shouldFetch: Boolean = false) {
//        workList.addSource(workRepository.getWorkList(shouldFetch)){
//            workList.removeSource(workRepository.getWorkList(shouldFetch))
//            workList.value = it
//        }
//    }

    fun test() = workRepository.getWorkList()

}