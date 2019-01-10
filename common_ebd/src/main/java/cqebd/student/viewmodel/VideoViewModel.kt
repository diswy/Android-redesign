package cqebd.student.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import cqebd.student.repository.VideoRepository
import cqebd.student.vo.CourseInfo
import cqebd.student.vo.VideoInfo
import xiaofu.lib.network.Resource
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2019/1/9.
 */
class VideoViewModel @Inject constructor(private val videoRepository: VideoRepository) : ViewModel() {

    val videoList = MediatorLiveData<Resource<List<VideoInfo>>>()
    val courseList = MediatorLiveData<Resource<List<CourseInfo>>>()

    fun getVideoList(shouldFetch: Boolean = false) {
        videoList.addSource(videoRepository.getVideoList(shouldFetch)) {
            videoList.removeSource(videoRepository.getVideoList(shouldFetch))
            videoList.value = it
        }
    }

    fun getCourseList(id: Int, shouldFetch: Boolean = false) {
        courseList.addSource(videoRepository.getCourseList(id, shouldFetch)) {
            courseList.removeSource(videoRepository.getCourseList(id, shouldFetch))
            courseList.value = it
        }
    }

}