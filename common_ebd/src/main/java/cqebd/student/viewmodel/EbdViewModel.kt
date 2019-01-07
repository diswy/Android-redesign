package cqebd.student.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cqebd.student.repository.EbdUserRepository
import cqebd.student.vo.User
import xiaofu.lib.network.Resource
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
class EbdViewModel @Inject constructor(private val userRepository: EbdUserRepository) : ViewModel() {

    fun login(account: String, password: String): LiveData<Resource<User>> {
        return userRepository.getUser(account, password, true)
    }


}