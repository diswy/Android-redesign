package cqebd.student.module.video.ui


import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import cqebd.student.module.video.R
import cqebd.student.module.video.databinding.FragmentHomeVideoBinding
import xiaofu.lib.base.adapter.BaseBindAdapter
import xiaofu.lib.base.fragment.BaseBindFragment


/**
 * 首页展示视频
 */
@Route(path = "/module_video/home")
class HomeVideoFragment : BaseBindFragment<FragmentHomeVideoBinding>() {
    private lateinit var adapter: BaseBindAdapter<String>

    override fun getLayoutRes(): Int = R.layout.fragment_home_video

    override fun initialize(activity: FragmentActivity, binding: FragmentHomeVideoBinding) {

        adapter = object : BaseBindAdapter<String>(R.layout.item_home_video) {
            override fun convert(helper: BaseBindHolder?, item: String?) {
                helper?.getBinding()?.setVariable(BR.title, item)
                helper?.getBinding()?.executePendingBindings()
            }
        }

        binding.include.rv.adapter = adapter

        adapter.setNewData(listOf("111111111111111",
                "2222222222222222",
                "http://img.hb.aicdn.com/d54629fac1e867c6ffb01b8ffd4529074eddafea3f981-1o4JDW_fw658",
                "4444444444444444",
                "http://img.hb.aicdn.com/00e8d7848c4369ea09d36350ab3ec3bd28a1c2e53a08-N1GmJA_fw658",
                "http://img.hb.aicdn.com/299f3585a8b0e9a26cda6b5da7cabdbe16e96a281dc70-bzbjFo_fw658"
        ))


    }

}
