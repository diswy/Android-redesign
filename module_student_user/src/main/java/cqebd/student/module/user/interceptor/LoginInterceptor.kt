package cqebd.student.module.user.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 * 登陆拦截器
 * Created by @author xiaofu on 2018/12/10.
 */
@Interceptor(priority = 10)
class LoginInterceptor : IInterceptor {

    override fun process(postcard: Postcard, callback: InterceptorCallback) {

        println("--->>>我就想证明一下我是否被拦截了")
        callback.onContinue(postcard)
    }

    override fun init(context: Context?) {

    }

}