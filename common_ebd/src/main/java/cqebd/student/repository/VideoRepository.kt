package cqebd.student.repository

import androidx.lifecycle.LiveData
import cqebd.student.cache.EbdMemoryCache
import cqebd.student.db.VideoDao
import cqebd.student.network.EbdVideoService
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.VideoInfo
import xiaofu.lib.network.ApiResponse
import xiaofu.lib.network.NetworkBoundResource
import xiaofu.lib.network.Resource
import xiaofu.lib.utils.RateLimiter
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2019/1/9.
 */
@Singleton
class VideoRepository @Inject constructor(
        private val videoService: EbdVideoService,
        private val videoDao: VideoDao,
        private val memoryCache: EbdMemoryCache
) {
    private val videoRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun getVideoList(shouldFetch: Boolean = false): LiveData<Resource<List<VideoInfo>>> {
        return object : NetworkBoundResource<List<VideoInfo>, BaseResponse<List<VideoInfo>>>() {
            override fun saveCallResult(item: BaseResponse<List<VideoInfo>>) {
                if (item.data != null) {
                    videoDao.insertVideoList(item.data)
                }
            }

            override fun shouldFetch(data: List<VideoInfo>?): Boolean {
                return data.isNullOrEmpty() or shouldFetch or videoRateLimit.shouldFetch("video"+6527)
            }

            override fun loadFromDb(): LiveData<List<VideoInfo>> {
                return videoDao.loadAllVideoList()
            }

            override fun createCall(): LiveData<ApiResponse<BaseResponse<List<VideoInfo>>>> {
//                return videoService.getCourseList(memoryCache.getId())
                return videoService.getCourseList(6527)
            }

        }.asLiveData()
    }

}