package xiaofu.lib.di.test

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2018/12/19.
 */
@Singleton
class UserRepository @Inject constructor(private val retrofit: Retrofit):AnkoLogger {
    fun hello(){
        AnkoLogger("xiaofu").info { "retrofit: $retrofit  " }
    }
}