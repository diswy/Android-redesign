package cqebd.student.module.user.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import cqebd.student.cache.USER
import cqebd.student.vo.User
import xiaofu.lib.BaseApp
import xiaofu.lib.cache.ACache

/**
 * 登陆拦截器
 * Created by @author xiaofu on 2018/12/10.
 */
@Interceptor(priority = 10)
class LoginInterceptor : IInterceptor {

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        callback.onContinue(postcard)

        // TODO : 登陆拦截，保险机制
//        val cache = ACache.get(BaseApp.instance)
//        val user = Gson().fromJson<User>(cache.getAsString(USER), User::class.java)
//        if (user != null) {
//            callback.onContinue(postcard)
//        } else {
//            val path = postcard.path
//            if (path == "/module_user/login") {
//                callback.onContinue(postcard)
//            } else {
//                ARouter.getInstance()
//                        .build("/module_user/login")
//                        .navigation()
//            }
//        }
    }

    override fun init(context: Context?) {

    }

}