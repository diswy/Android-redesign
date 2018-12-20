package cqebd.student.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import cqebd.student.network.Resource
import cqebd.student.repository.EbdUserRepository
import cqebd.student.vo.BaseResponse
import cqebd.student.vo.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
class EbdViewModel @Inject constructor(private val userRepository: EbdUserRepository) : ViewModel() {

    val a = MutableLiveData<String>()

    fun login(account: String, password: String): LiveData<Resource<BaseResponse<User>>> {
        a.value = "zhang li fu "

        userRepository.testCache()
        return userRepository.getUser(account, password)
    }

    fun say() {
        println("------>>>>>> userRepository  : $userRepository")
    }

}