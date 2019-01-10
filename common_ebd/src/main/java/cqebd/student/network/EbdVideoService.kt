package cqebd.student.network

import androidx.lifecycle.LiveData
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.CourseInfo
import cqebd.student.vo.VideoInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xiaofu.lib.network.ApiResponse

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
interface EbdVideoService {
    /**
     * 获取课程列表
     */
    @FormUrlEncoded
    @POST("api/CoursePeriod/GetCourseList")
    fun getCourseList(
            @Field("studentid") userId: Int,
            @Field("Type") type: Int = 2)
            : LiveData<ApiResponse<BaseResponse<List<VideoInfo>>>>


    /**
     * 获取课程下子课时列表
     */
    @FormUrlEncoded
    @POST("api/CoursePeriod/GetPeriodList")
    fun getPeriodCourseList(
            @Field("CourseId") id: Int,
            @Field("studentid") userId: Int)
            : LiveData<ApiResponse<BaseResponse<List<CourseInfo>>>>

}