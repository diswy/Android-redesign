package cqebd.student.repository

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
@Singleton
class EbdUserRepository @Inject constructor(private val retrofit:Retrofit){

    fun say(){
        println("------>>>>>> retrofit  : $retrofit")
    }
}