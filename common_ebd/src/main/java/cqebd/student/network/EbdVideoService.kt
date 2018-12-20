package cqebd.student.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
            @Field("Type") type: Int = 2,
            @Field("studentid") userId: Long)
            : Call<String>
}