package cqebd.student.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import cqebd.student.repository.VideoRepository
import cqebd.student.vo.VideoInfo
import xiaofu.lib.network.Resource
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2019/1/9.
 */
class VideoViewModel @Inject constructor(private val videoRepository: VideoRepository) : ViewModel() {

    val videoList = MediatorLiveData<Resource<List<VideoInfo>>>()

    fun getVideoList(){
        videoList.addSource(videoRepository.getVideoList()){
            videoList.removeSource(videoRepository.getVideoList())
            videoList.value = it
        }
    }

}