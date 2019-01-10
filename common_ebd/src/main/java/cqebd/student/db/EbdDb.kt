package cqebd.student.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cqebd.student.vo.CourseInfo
import cqebd.student.vo.VideoInfo

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
@Database(
        entities = [VideoInfo::class,
            CourseInfo::class],
        version = 1,
        exportSchema = false
)
abstract class EbdDb : RoomDatabase() {

    abstract fun videoDao(): VideoDao
}