package cqebd.student.module.user.ui

import com.alibaba.android.arouter.facade.annotation.Route
import cqebd.student.module.user.R
import xiaofu.lib.base.activity.BaseActivity

@Route(path = "/ebd/student/user/login")
class LoginActivity : BaseActivity() {

    override fun isTranslucentMode(): Boolean = true

    override fun isStatusDarkMode(): Boolean = true

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun initialize() {

    }

}
