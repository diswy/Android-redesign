package xiaofu.lib.base.activity

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import xiaofu.lib.base.R

/**
 *
 * Created by @author xiaofu on 2018/11/30.
 */
abstract class BaseToolbarBindActivity<T : ViewDataBinding> : BaseBindActivity<T>() {

    override fun setView() {
        setContentView(R.layout.activity_base_toolbar_bind)
        val parent = findViewById<FrameLayout>(R.id.container)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutRes(), parent, true)
    }
}