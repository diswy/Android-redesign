package cqebd.student.module.user.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import cqebd.student.module.user.R
import cqebd.student.module.user.databinding.FragmentHomeUserBinding
import xiaofu.lib.base.fragment.BaseBindFragment


/**
 * 首页展示的用户信息
 */
@Route(path = "/ebd/student/user/home")
class HomeUserFragment : BaseBindFragment<FragmentHomeUserBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home_user

    override fun initialize(activity: FragmentActivity, binding: FragmentHomeUserBinding) {

    }

}
