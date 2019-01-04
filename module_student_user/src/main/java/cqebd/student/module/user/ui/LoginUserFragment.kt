package cqebd.student.module.user.ui

import android.text.TextUtils
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import cqebd.student.module.user.R
import cqebd.student.module.user.databinding.FragmentLoginUserBinding
import cqebd.student.viewmodel.EbdViewModel
import org.jetbrains.anko.design.snackbar
import xiaofu.lib.BaseApp
import xiaofu.lib.base.fragment.BaseBindFragment
import xiaofu.lib.inline.onClick
import xiaofu.lib.network.Status

/**
 * 登陆界面
 * Created by @author xiaofu on 2018/12/15.
 */
class LoginUserFragment : BaseBindFragment<FragmentLoginUserBinding>() {

    private lateinit var model: EbdViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_login_user

    override fun initialize(activity: FragmentActivity, binding: FragmentLoginUserBinding) {
        model = ViewModelProviders.of(activity, BaseApp.instance.factory).get(EbdViewModel::class.java)
        binding.userModel = model
        binding.setLifecycleOwner(this)
    }

    override fun bindListener(binding: FragmentLoginUserBinding) {
        binding.loginBtnSubmit.onClick {
            login(it, binding.loginName.text.toString(), binding.loginPwd.text.toString())
        }
        binding.btnFindAccount.onClick {
            Navigation.findNavController(it).navigate(R.id.action_loginUserFragment_to_findUserFragment)
        }
        binding.btnResetPwd.onClick {
            Navigation.findNavController(it).navigate(R.id.action_loginUserFragment_to_resetPwdFragment)
        }

        model.login("xsebd01", "123456").observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    println("--->>> SUCCESS")
                    println("--->>> SUCCESS : ${it.data}")
                }
                Status.ERROR -> {
                    println("--->>> ERROR,${it.data},error:${it.throwable}")
                }
                Status.LOADING -> {
                    println("--->>> LOADING")
                }
            }
        })
    }

    private fun login(view: View, account: String, pwd: String) {
        if (TextUtils.isEmpty(account)) {
            view.snackbar("请输入账号")
            return
        }
        if (TextUtils.isEmpty(pwd)) {
            view.snackbar("请输入密码")
            return
        }
        model.login(account.trim(), pwd.trim()).observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    println("--->>> SUCCESS")
                }
                Status.ERROR -> {
                    println("--->>> ERROR,${it.data},error:${it.throwable}")
                }
                Status.LOADING -> {
                    println("--->>> LOADING")
                }
            }
        })
    }
}