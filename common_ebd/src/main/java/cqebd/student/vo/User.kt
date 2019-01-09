package cqebd.student.vo

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by @author xiaofu on 2018/12/18.
 */
data class User(@SerializedName("studentId")
                val ID: Int,
                val Name: String,// 学生姓名
                val LoginName: String,// 登陆账号
                @SerializedName("Tel")
                var Phone: String,// 手机号
                @SerializedName("Photo")
                var Avatar: String,// 头像
                val Gender: String,// 性别
                @SerializedName("SubjectType")
                val SubjectList: List<Subject>,// 科目
                @SerializedName("ExaminationPapersType")
                val JobTypeList: List<JobType>,// 作业类型
                val ImagesUrl: String,
                val ImagesTag: String,
                val OssAccessUrl: String,
                val OssAccessUrlTag: String,
                var Flower: Int,// 红花数量
                val IsGroup: Boolean)// 是否是小组长
{
    //科目
    data class Subject(val Id: Int,
                       val Name: String,
                       val Status: Int)

    //作业类型
    data class JobType(val Id: Int,
                       val Name: String)
}