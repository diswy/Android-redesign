package cqebd.student.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cqebd.student.vo.Attachment

/**
 *
 * Created by @author xiaofu on 2019/2/21.
 */
class Converter {

    @TypeConverter
    fun fromAttachmentList(value: List<Attachment>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Attachment>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAttachmentList(value: String): List<Attachment> {
        val gson = Gson()
        val type = object : TypeToken<List<Attachment>>() {}.type
        return gson.fromJson(value, type)
    }
}