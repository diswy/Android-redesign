package cqebd.student.module.work.ui

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.just.agentweb.AgentWeb
import cqebd.student.module.work.R
import cqebd.student.module.work.databinding.ActivityLookResultWebBinding
import cqebd.student.network.EbdUrl
import cqebd.student.vo.WorkInfo
import xiaofu.lib.base.activity.BaseBindActivity

@Route(path = "/module_work/web/look_result")
class LookResultWebActivity : BaseBindActivity<ActivityLookResultWebBinding>() {

    @Autowired
    @JvmField
    var workInfo: WorkInfo? = null

    private var agentWeb: AgentWeb? = null

    override fun getLayoutRes(): Int = R.layout.activity_look_result_web

    override fun initialize(binding: ActivityLookResultWebBinding) {
        ARouter.getInstance().inject(this)

        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(binding.webContainer, FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(EbdUrl.HOMEWORK_END_LOOK.format(workInfo?.TaskId))
    }

    override fun onResume() {
        agentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onPause() {
        agentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        agentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }
}
