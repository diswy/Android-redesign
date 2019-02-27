package cqebd.student.module.work.ui


import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import cqebd.student.module.work.R
import cqebd.student.module.work.databinding.FragmentHomeWorkBinding
import xiaofu.lib.base.fragment.BaseBindFragment
import xiaofu.lib.inline.onClick

/**
 * 首页作业模块
 */
@Route(path = "/module_work/home")
class HomeWorkFragment : BaseBindFragment<FragmentHomeWorkBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home_work

    override fun initialize(activity: FragmentActivity, binding: FragmentHomeWorkBinding) {

    }

    override fun bindListener(binding: FragmentHomeWorkBinding) {
        binding.btnDoWork.onClick {
            ARouter.getInstance()
                    .build("/module_work/do_work_list")
                    .navigation()
        }
    }

}
