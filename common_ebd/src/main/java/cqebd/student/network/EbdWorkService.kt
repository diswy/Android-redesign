package cqebd.student.network

import androidx.lifecycle.LiveData
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.User
import cqebd.student.vo.WorkInfo
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import xiaofu.lib.network.ApiResponse

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
interface EbdWorkService {

    /**
     * 用户登录
     */
    @GET("api/Account/Login")
    fun userLogin(
            @Query("loginName") loginName: String,
            @Query("pwd") pwd: String)
            : LiveData<ApiResponse<BaseResponse<User>>>


    /**
     * 获取作业列表
     */
    @POST("api/Students/GetExaminationTasks")
    fun getWorkList(
            @Query("userid") loginId: Int,
            @Query("SubjectTypeID") SubjectTypeID: Int?,
            @Query("ExaminationPapersTypeID") ExaminationPapersTypeID: Int?,
            @Query("status") status: Int?,
            @Query("pageindex") pageIndex: Int,
            @Query("pagesieze") pageSize: Int = 20)
            : LiveData<ApiResponse<BaseResponse<List<WorkInfo>>>>
}