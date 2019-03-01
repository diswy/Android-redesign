package cqebd.student.module.work.ui

import android.graphics.Color
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.Logger
import cqebd.student.module.work.R
import cqebd.student.module.work.databinding.ActivityWorkListBinding
import cqebd.student.viewmodel.WorkViewModel
import cqebd.student.vo.WorkInfo
import xiaofu.lib.BaseApp
import xiaofu.lib.base.activity.BaseBindActivity
import xiaofu.lib.base.adapter.BaseBindAdapter
import xiaofu.lib.network.Status
import xiaofu.lib.view.drawable.DrawableBuilder

@Route(path = "/module_work/do_work_list")
class WorkListActivity : BaseBindActivity<ActivityWorkListBinding>() {
    private lateinit var adapter: BaseBindAdapter<WorkInfo>
    private lateinit var model: WorkViewModel

    override fun getLayoutRes(): Int = R.layout.activity_work_list

    override fun initialize(binding: ActivityWorkListBinding) {
        model = ViewModelProviders.of(this, BaseApp.instance.factory).get(WorkViewModel::class.java)

        adapter = object : BaseBindAdapter<WorkInfo>(R.layout.item_work_normal) {
            override fun convert(helper: BaseBindHolder?, item: WorkInfo?) {
                helper ?: return
                item ?: return

                helper.getBinding().setVariable(BR.workNormalInfo, item)
                helper.getBinding().executePendingBindings()

                val status = when (item.Status) {
                    -1 -> "新作业"
                    0 -> "答题中"
                    1 -> "已完成"
                    2 -> "已批阅"
                    else -> "默认"
                }
                helper.setText(R.id.tv_status, status)
            }
        }

        binding.include.rv.adapter = adapter

        val divider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        val mDrawable = DrawableBuilder()
                .solidColor(Color.RED)
                .height(2)
                .build()
        divider.setDrawable(mDrawable)
        binding.include.rv.addItemDecoration(divider)

        model.workList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.setNewData(it.data?.data)
                    Logger.d(it.data?.data)
                }
                Status.ERROR -> {
                    handleExceptions(it.throwable)
                }
                Status.LOADING -> {
                    Logger.d("作业列表加载中...")
                }
            }
        })

        model.getWorkList()
    }

    override fun bindListener(binding: ActivityWorkListBinding) {

        adapter.setOnItemClickListener { _, _, position ->
            // 根据状态进入不同界面
            // status -1 初始状态、0 答题中、1 已交卷、2 已批阅
            val item = adapter.getItem(position) ?: return@setOnItemClickListener
            when (item.Status) {
                -1, 0 -> {
                    ARouter.getInstance()
                            .build("/module_work/web/look_result")
                            .withParcelable("workInfo", item)
                            .navigation()
                }
                1, 2 -> {
                    ARouter.getInstance()
                            .build("/module_work/web/look_result")
                            .withParcelable("workInfo", item)
                            .navigation()
                }
            }
        }
    }
}
