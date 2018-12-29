package cqebd.student.module.user.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity

import cqebd.student.module.user.R
import cqebd.student.module.user.databinding.FragmentFindUserBinding
import xiaofu.lib.base.fragment.BaseBindFragment


/**
 * 找回账号
 * Created by @author xiaofu on 2018/12/29.
 */
class FindUserFragment : BaseBindFragment<FragmentFindUserBinding>() {

    override fun getLayoutRes(): Int = R.layout.fragment_find_user

    override fun initialize(activity: FragmentActivity, binding: FragmentFindUserBinding) {

    }
}
