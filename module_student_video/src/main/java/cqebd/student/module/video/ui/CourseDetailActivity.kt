package cqebd.student.module.video.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import cqebd.student.module.video.R
import cqebd.student.module.video.databinding.ActivityCourseDetailBinding
import cqebd.student.viewmodel.VideoViewModel
import cqebd.student.vo.CourseInfo
import cqebd.student.vo.VideoInfo
import org.jetbrains.anko.toast
import xiaofu.lib.BaseApp
import xiaofu.lib.base.activity.BaseToolbarBindActivity
import xiaofu.lib.base.adapter.BaseBindAdapter
import xiaofu.lib.network.Status

@Route(path = "/module_video/course_detail")
class CourseDetailActivity : BaseToolbarBindActivity<ActivityCourseDetailBinding>() {

    @Autowired
    lateinit var img: String
    @Autowired
    @JvmField
    var id: Int = 0

    private lateinit var adapter: BaseBindAdapter<CourseInfo>
    private lateinit var model: VideoViewModel


    override fun getLayoutRes(): Int = R.layout.activity_course_detail

    override fun initialize(binding: ActivityCourseDetailBinding) {
        ARouter.getInstance().inject(this)
        binding.courseImg = img

        model = ViewModelProviders.of(this, BaseApp.instance.factory).get(VideoViewModel::class.java)

        toast(id.toString())

        adapter = object : BaseBindAdapter<CourseInfo>(R.layout.item_detail_course) {
            override fun convert(helper: BaseBindHolder?, item: CourseInfo?) {
                helper?.getBinding()?.setVariable(BR.courseInfo, item)
                helper?.getBinding()?.executePendingBindings()
            }
        }

        binding.include.rv.adapter = adapter

        model.courseList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.include.refreshLayout.finishRefresh(true)
                    adapter.setNewData(it.data)
                }
                Status.ERROR -> {
                    handleExceptions(it.throwable)
                }
                Status.LOADING -> {
                }
            }
        })

        model.getCourseList(id)
    }

}
