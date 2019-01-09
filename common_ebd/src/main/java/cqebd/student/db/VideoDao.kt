package cqebd.student.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cqebd.student.vo.VideoInfo

/**
 *
 * Created by @author xiaofu on 2019/1/9.
 */
@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideoList(videoLists: List<VideoInfo>)

    @Query("SELECT * FROM Videoinfo")
    fun loadAllVideoList(): LiveData<List<VideoInfo>>
}