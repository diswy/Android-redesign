package cqebd.student.module.video.ui


import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import cqebd.student.module.video.R
import cqebd.student.module.video.databinding.FragmentHomeVideoBinding
import xiaofu.lib.base.fragment.BaseBindFragment


/**
 * 首页展示视频
 */
@Route(path = "/module_video/home")
class HomeVideoFragment : BaseBindFragment<FragmentHomeVideoBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_home_video

    override fun initialize(activity: FragmentActivity, binding: FragmentHomeVideoBinding) {

    }

}
