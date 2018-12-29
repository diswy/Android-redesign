package cqebd.student.network

import androidx.lifecycle.LiveData
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.User
import io.reactivex.Flowable
import retrofit2.http.GET
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


    @GET("api/Account/Login")
    fun test(
            @Query("loginName") loginName: String,
            @Query("pwd") pwd: String)
            : LiveData<ApiResponse<BaseResponse<User>>>
}