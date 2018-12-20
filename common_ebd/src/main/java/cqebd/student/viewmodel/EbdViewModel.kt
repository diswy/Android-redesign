package cqebd.student.viewmodel

import androidx.lifecycle.ViewModel
import cqebd.student.repository.EbdUserRepository
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
class EbdViewModel @Inject constructor(private val userRepository: EbdUserRepository) : ViewModel(){

    fun say() {
        println("------>>>>>> userRepository  : $userRepository")
        userRepository.say()
    }

}