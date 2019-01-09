package cqebd.student.viewmodel

import androidx.lifecycle.ViewModel
import cqebd.student.repository.UserRepository
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    fun login(account: String, password: String) = userRepository.getUser(account, password)

}