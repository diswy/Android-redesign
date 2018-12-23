package cqebd.student.module.user.ui

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cqebd.student.module.user.R
import cqebd.student.module.user.databinding.FragmentLoginUserBinding
import cqebd.student.viewmodel.EbdViewModel
import xiaofu.lib.BaseApp
import xiaofu.lib.base.fragment.BaseBindFragment
import xiaofu.lib.network.Status

/**
 *
 * Created by @author xiaofu on 2018/12/15.
 */
class LoginUserFragment : BaseBindFragment<FragmentLoginUserBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login_user

    override fun initialize(activity: FragmentActivity, binding: FragmentLoginUserBinding) {
        val model = ViewModelProviders.of(activity, BaseApp.instance.factory).get(EbdViewModel::class.java)
        binding.userModel = model
        binding.account = "xsc001"
        binding.pwd = "123456"
        binding.setLifecycleOwner(this)

        model.b.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    println("------ Frag BBBBBBB LOADING")
                }
                Status.SUCCESS -> {
                    println("------ Frag BBBBBBB SUCCESS data:${it.data}")
                }
                Status.ERROR -> {
                    println("------ Frag BBBBBBB ERROR  error:${it.throwable}")
                }
            }
        })

        refresh(model)
    }

    private fun refresh(model: EbdViewModel) {
        model.login("xsc001", "123456").observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    println("------ Frag LOADING")
                }
                Status.SUCCESS -> {
                    println("------ Frag SUCCESS data :${it.data}")
                }
                Status.ERROR -> {
                    println("------ Frag ERROR")
                    println("------ Frag ERROR  error:${it.throwable}")
                }
            }
        })
    }

}