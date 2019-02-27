package cqebd.student.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * Created by @author xiaofu on 2019/2/21.
 */
@Parcelize
data class Attachment(
        val Id: Int,
        val ExaminationPapersId: Int,
        val Status: Int,
        val Type: Int,
        val QuestionId: Int,
        val AnswerType: Int,// 1播放中答、2是必须播放完后可答
        val CanWatchTimes: Int,// 最多观看次数，0无限制
        val Name: String,
        val MediaTypeName: String,
        val CreateDateTime: String,
        val Remarks: String,
        val Url: String,
        val VideoId: String
) : Parcelable