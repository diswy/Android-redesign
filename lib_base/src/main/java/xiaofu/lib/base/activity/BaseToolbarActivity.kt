package xiaofu.lib.base.activity

import android.view.LayoutInflater
import android.widget.FrameLayout
import xiaofu.lib.base.R

/**
 *
 * Created by @author xiaofu on 2018/12/3.
 */
abstract class BaseToolbarActivity : BaseActivity() {

    override fun setView() {
        setContentView(R.layout.activity_base_toolbar_bind)
        val parent = findViewById<FrameLayout>(R.id.container)
        LayoutInflater.from(this).inflate(getLayoutRes(), parent, true)
    }
}