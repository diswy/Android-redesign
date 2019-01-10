package cqebd.student.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Created by @author xiaofu on 2019/1/10.
 */
@Entity
data class CourseInfo(
        @PrimaryKey val Id: Int,
        @ColumnInfo(name = "CourseId") val CourseId: Int,
        @ColumnInfo(name = "Day") val Day: String,
        @ColumnInfo(name = "Durartion") val Durartion: Int,
        @ColumnInfo(name = "GradeId") val GradeId: Int,
        @ColumnInfo(name = "GradeName") val GradeName: String,
        @ColumnInfo(name = "HasChannel") val HasChannel: Int,
        @ColumnInfo(name = "HasChat") val HasChat: Int,
        @ColumnInfo(name = "HasIWB") val HasIWB: Int,
        @ColumnInfo(name = "HasVchat") val HasVchat: Int,
        @ColumnInfo(name = "IsFeedback") val IsFeedback: Boolean,
        @ColumnInfo(name = "LiveProvider") val LiveProvider: String,
        @ColumnInfo(name = "Name") val Name: String,
        @ColumnInfo(name = "PlanStartDate") val PlanStartDate: String,
        @ColumnInfo(name = "RegisterNumber") val RegisterNumber: Int,
        @ColumnInfo(name = "SchoolId") val SchoolId: Int,
        @ColumnInfo(name = "Snapshoot") val Snapshoot: String,
        @ColumnInfo(name = "StartDateTime") val StartDateTime: Long,
        @ColumnInfo(name = "Status") val Status: Int,
        @ColumnInfo(name = "SubjectTypeId") val SubjectTypeId: Int,
        @ColumnInfo(name = "TeacherId") val TeacherId: Int,
        @ColumnInfo(name = "TeacherName") val TeacherName: String,
        @ColumnInfo(name = "TeachingMaterialSectionId") val TeachingMaterialSectionId: Int,
        @ColumnInfo(name = "TeachingMaterialTypeId") val TeachingMaterialTypeId: Int,
        @ColumnInfo(name = "Type") val Type: Int
)