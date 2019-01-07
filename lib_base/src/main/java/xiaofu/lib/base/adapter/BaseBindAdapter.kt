package xiaofu.lib.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import xiaofu.lib.base.R

/**
 *
 * Created by @author xiaofu on 2019/1/7.
 */
abstract class BaseBindAdapter<T>
constructor(layoutId: Int)
    : BaseQuickAdapter<T, BaseBindAdapter.BaseBindHolder>(layoutId, null) {

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        val binding: ViewDataBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)// 这个ID唯一即可，可自由生成
        return view
    }

    class BaseBindHolder(view: View?) : BaseViewHolder(view) {
        fun getBinding(): ViewDataBinding {
            return itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ViewDataBinding
        }
    }
}