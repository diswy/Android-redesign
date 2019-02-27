package cqebd.student.module.work.ui

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
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
                helper?.getBinding()?.setVariable(BR.workNormalInfo, item)
                helper?.getBinding()?.executePendingBindings()
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
                    println("--->>>数据 获取成功:数据内容：${it.data}")
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

//        model.getWorkList()

        model.test().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Logger.d(it.data?.data)
                }
                Status.ERROR -> {
                    handleExceptions(it.throwable)
                }
                Status.LOADING -> {
                    println("--->>> LOADING")
                }
            }
        })
    }

    override fun bindListener(binding: ActivityWorkListBinding) {

        adapter.setOnItemClickListener { _, _, position ->
            // 根据状态进入不同界面
            // status -1 初始状态、0 答题中、1 已交卷、2 已批阅
            val item = adapter.getItem(position)
        }
    }
}
