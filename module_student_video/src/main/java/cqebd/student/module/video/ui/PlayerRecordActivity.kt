package cqebd.student.module.video.ui

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import cqebd.student.module.video.R
import cqebd.student.module.video.databinding.ActivityPlayerRecordBinding
import cqebd.student.vo.CourseInfo
import xiaofu.lib.base.activity.BaseBindActivity

/**
 * 录播页面
 */
@Route(path = "/module_video/player_record")
class PlayerRecordActivity : BaseBindActivity<ActivityPlayerRecordBinding>() {

    @Autowired
    @JvmField
    var info:CourseInfo? = null

    override fun getLayoutRes(): Int = R.layout.activity_player_record

    override fun initialize(binding: ActivityPlayerRecordBinding) {
        ARouter.getInstance().inject(this)

        println("------>>>$info")

    }
}
