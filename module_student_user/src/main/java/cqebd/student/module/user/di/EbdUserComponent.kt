package cqebd.student.module.user.di

import androidx.fragment.app.FragmentActivity
import cqebd.student.module.user.ui.LoginActivity
import cqebd.student.module.user.ui.LoginUserFragment
import cqebd.student.module.user.viewmodel.UserViewModel
import dagger.Component
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2018/12/18.
 */
@Singleton
@Component
interface EbdUserComponent {
//    fun inject(frag: LoginUserFragment)
    fun inject(aty: FragmentActivity)
}