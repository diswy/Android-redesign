package xiaofu.lib

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.squareup.leakcanary.LeakCanary
import xiaofu.lib.base.IBaseApplication
import xiaofu.lib.di.DaggerAppComponent
import javax.inject.Inject

/**
 *
 * Created by @author xiaofu on 2018/12/18.
 */
class BaseApp : Application() {
    private val moduleList = arrayOf(
            "cqebd.student.module.user.UserApp",
            "cqebd.student.module.video.VideoApp")

    companion object {
        lateinit var instance: BaseApp
            private set
    }

    /**
     * 用于生产ViewModel，注入单例成员
     * 例如：OKHttpClient、Retrofit、以及保证内容库是单例的
     */
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate() {
        super.onCreate()
        instance = this

        DaggerAppComponent.builder().application(this)
                .build().inject(this)

        if (LeakCanary.isInAnalyzerProcess(this))
            return
        LeakCanary.install(this)

        ARouter.openDebug()
        ARouter.init(this)

        modulesAppInit()
        registerNetworkChanged()
    }

    /**
     * 利用反射对不同的Module进行初始化操作
     */
    private fun modulesAppInit() {
        for (moduleImpl in moduleList) {
            try {
                val clazz = Class.forName(moduleImpl)
                val obj = clazz.newInstance()
                if (obj is IBaseApplication) {
                    obj.init(instance)
                }
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun registerNetworkChanged() {
        val conn = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()
        builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        builder.addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
//        builder.addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//        builder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        conn.registerNetworkCallback(builder.build(), object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                super.onUnavailable()
                println("--->>> onUnavailable")

            }
            override fun onAvailable(network: Network?) {
                super.onAvailable(network)
                println("--->>> onAvailable")
            }

            override fun onLost(network: Network?) {
                super.onLost(network)
                println("--->>> onLost")
            }
        })
    }
}