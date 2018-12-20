package cqebd.student.module.user.ui

import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import cqebd.student.module.user.R
import cqebd.student.viewmodel.EbdViewModel
import xiaofu.lib.BaseApp
import xiaofu.lib.base.activity.BaseActivity
import xiaofu.lib.tools.oom.HuaWeiFix

@Route(path = "/module_user/login")
class LoginActivity : BaseActivity() {

    override fun isTranslucentMode(): Boolean = true

    override fun isStatusDarkMode(): Boolean = true

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun initialize() {

    }

    override fun onDestroy() {
        super.onDestroy()
        HuaWeiFix.fixLeak(this)
    }
}
