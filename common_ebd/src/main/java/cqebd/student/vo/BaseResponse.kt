package cqebd.student.vo

/**
 *
 * Created by @author xiaofu on 2018/12/10.
 */
data class BaseResponse<out T>(
        val errorId: Int,
        val message: String,
        val isSuccess: Boolean,
        val data: T?
)