package cqebd.student.vo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 作业列表信息
 * Created by @author xiaofu on 2019/2/21.
 */
@Parcelize
data class WorkInfo(
        @SerializedName("StudentQuestionsTasksID")
        val TaskId: Long,
        @SerializedName("ExaminationPapersId")
        val PapersId: Long,
        @SerializedName("ExaminationPapersPushId")
        val PushId: Long,
        @SerializedName("ExaminationPapersTypeId")
        val TypeId: Int,
        @SerializedName("PapersTypeName")
        val TypeName: String,
        var Status: Int,
        val IsTasks: Boolean,
        val Name: String,
        @SerializedName("SubjectTypeId")
        val SubjectId: Int,
        @SerializedName("SubjectTypeName")
        val SubjectName: String,
        val CanStartDateTime: String,
        var StartTime: String?,
        val CanEndDateTime: String,
        val EndTime: String?,
        val Count: Int,
        var Duration: Int,
        @SerializedName("PuchDateTime")
        var publishTime: String,
        @SerializedName("ExaminationPapersAttachment")
        val attachments: List<Attachment>?,
        val IsMedal: Boolean
) : Parcelable