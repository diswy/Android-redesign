package cqebd.student.module.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cqebd.student.module.user.repository.UserRepository
import cqebd.student.vo.User
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/18.
 */
class UserViewModel : ViewModel() {

    private lateinit var user: MutableLiveData<User>


    fun getUser(): LiveData<User> {
        if (!::user.isInitialized) {
            user = MutableLiveData()
            loadUser()
        }
        return user
    }

    private fun loadUser() {
//        val userCache = EbdMemoryCache.INSTANCE.getUser()
//        if (userCache != null) {
//            user.value = userCache
//        } else {
//
//        }
    }
}