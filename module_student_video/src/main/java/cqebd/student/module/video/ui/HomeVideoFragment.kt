package cqebd.student.module.video.ui


import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import cqebd.student.module.video.R
import cqebd.student.module.video.databinding.FragmentHomeVideoBinding
import cqebd.student.viewmodel.VideoViewModel
import cqebd.student.vo.VideoInfo
import xiaofu.lib.BaseApp
import xiaofu.lib.base.adapter.BaseBindAdapter
import xiaofu.lib.base.fragment.BaseBindFragment
import xiaofu.lib.network.Status


/**
 * 首页展示视频
 */
@Route(path = "/module_video/home")
class HomeVideoFragment : BaseBindFragment<FragmentHomeVideoBinding>() {
    private lateinit var adapter: BaseBindAdapter<VideoInfo>
    private lateinit var model: VideoViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_home_video

    override fun initialize(activity: FragmentActivity, binding: FragmentHomeVideoBinding) {
        model = ViewModelProviders.of(this, BaseApp.instance.factory).get(VideoViewModel::class.java)

        adapter = object : BaseBindAdapter<VideoInfo>(R.layout.item_home_video) {
            override fun convert(helper: BaseBindHolder?, item: VideoInfo?) {
                helper?.getBinding()?.setVariable(BR.info, item)
                helper?.getBinding()?.executePendingBindings()
            }
        }

        binding.include.rv.adapter = adapter

        model.videoList.observe(this, Observer {
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

        model.getVideoList()
    }

    override fun bindListener(binding: FragmentHomeVideoBinding) {
        binding.include.refreshLayout.setOnRefreshListener {
            model.getVideoList(true)
        }

        adapter.setOnItemClickListener { _, _, position ->

            val item = adapter.getItem(position) ?: return@setOnItemClickListener

            ARouter.getInstance().build("/module_video/course_detail")
                    .withString("img", item.Snapshoot)
                    .withInt("id", item.Id)
                    .navigation()
        }
    }

}
