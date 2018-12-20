package cqebd.student.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cqebd.student.network.Resource
import cqebd.student.repository.EbdUserRepository
import cqebd.student.vo.User
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
class EbdViewModel @Inject constructor(private val userRepository: EbdUserRepository) : ViewModel() {


    val b = MediatorLiveData<Resource<User>>()
    val user = MutableLiveData<User>()

    fun test(){
        val call = userRepository.getUser("xsc001", "123456")
        b.addSource(call){
            b.value = it
            user.value = it.data
        }
    }


    fun login(account: String, password: String): LiveData<Resource<User>> {
        return userRepository.getUser(account, password)
    }

}