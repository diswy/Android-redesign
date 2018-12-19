package xiaofu.lib.di.test

import androidx.lifecycle.ViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/19.
 */

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(), AnkoLogger {

    fun say() {
        AnkoLogger("xiaofu").info { "userRepository: $userRepository  " }
        userRepository.hello()
    }

}

