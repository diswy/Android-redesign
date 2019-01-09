package cqebd.student.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * Created by @author xiaofu on 2019/1/9.
 */
@Entity
data class VideoInfo(
        @PrimaryKey var Id: Int,
        @ColumnInfo(name = "EndDateTime") var EndDateTime: String,
        @ColumnInfo(name = "GradeId") var GradeId: Int,
        @ColumnInfo(name = "GradeName") var GradeName: String,
        @ColumnInfo(name = "IsFeedback") var IsFeedback: Boolean,
        @ColumnInfo(name = "IsStudentCollect") var IsStudentCollect: Boolean,
        @ColumnInfo(name = "Name") var Name: String,
        @ColumnInfo(name = "PeriodCount") var PeriodCount: Int,
        @ColumnInfo(name = "Price") var Price: Double,
        @ColumnInfo(name = "RegisterNumber") var RegisterNumber: Int,
        @ColumnInfo(name = "SchoolId") var SchoolId: Int,
        @ColumnInfo(name = "SchoolName") var SchoolName: String,
        @ColumnInfo(name = "SchoolTermTypeId") var SchoolTermTypeId: Int,
        @ColumnInfo(name = "SchoolTermTypeName") var SchoolTermTypeName: String,
        @ColumnInfo(name = "Snapshoot") var Snapshoot: String,
        @ColumnInfo(name = "StartDate") var StartDate: String,
        @ColumnInfo(name = "Status") var Status: Int,
        @ColumnInfo(name = "SubjectTypeId") var SubjectTypeId: Int,
        @ColumnInfo(name = "SubjectTypeName") var SubjectTypeName: String,
        @ColumnInfo(name = "TeacherId") var TeacherId: Int,
        @ColumnInfo(name = "TeacherName") var TeacherName: String,
        @ColumnInfo(name = "TeacherPhoto") var TeacherPhoto: String,
        @ColumnInfo(name = "TeachingMaterialTypeName") var TeachingMaterialTypeName: String,
        @ColumnInfo(name = "Type") var Type: Int
)