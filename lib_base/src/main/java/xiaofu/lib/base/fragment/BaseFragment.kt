package xiaofu.lib.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import xiaofu.lib.base.timer.ITimer
import kotlinx.coroutines.*
import org.jetbrains.anko.AnkoLogger
import kotlin.coroutines.CoroutineContext

/**
 *
 * Created by @author xiaofu on 2018/12/7.
 */
abstract class BaseFragment : Fragment(), AnkoLogger, CoroutineScope {
    /**
     * 获取视图ID
     */
    abstract fun getLayoutRes(): Int

    /**
     * 带Tag标签的日志
     * note:真机调试中，info以下级别的日志容易被过滤不显示
     */
    protected val log = AnkoLogger("xiaofu")

    protected lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            initialize(it)
            bindListener()
        } ?: throw RuntimeException("Invalid Activity")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
    }

    abstract fun initialize(activity: FragmentActivity)

    protected open fun bindListener() {

    }

    /**
     * 计时任务,如果只关心过程或结果可使用子类接口
     * @param second 秒
     * @param timer 回调
     */
    protected open fun timer(second: Int, timer: ITimer) {
        launch {
            for (i in second downTo 1) {
                timer.onTime(i)
                delay(1000L)
            }
            timer.onTimeEnd()
        }
    }

}