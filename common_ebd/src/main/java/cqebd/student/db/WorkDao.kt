package cqebd.student.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cqebd.student.vo.WorkInfo

/**
 *
 * Created by @author xiaofu on 2019/2/21.
 */
@Dao
interface WorkDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertWorkList(workLists: List<WorkInfo>)
//
//    @Query("SELECT * FROM WorkInfo")
//    fun loadAllWorkList(): LiveData<List<WorkInfo>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCourseList(courseLists: List<CourseInfo>)
//
//    @Query("SELECT * FROM CourseInfo WHERE CourseId = :id")
//    fun loadCourseById(id: Int): LiveData<List<CourseInfo>>
}