package xiaofu.lib.base.http

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络客户端
 * Created by @author xiaofu on 2018/12/8.
 */
class HttpManager{

    private object Instance {
        val instance = HttpManager()
    }

    companion object {
        val INSTANCE = Instance.instance
    }

    /**
     * 方便开发者调试接口，使用前先
     */
    private var chuckInterceptor: ChuckInterceptor? = null

    fun initChuck(context: Context) {
        chuckInterceptor = ChuckInterceptor(context)
    }

    /**
     * 配置OKHttp
     */
    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        chuckInterceptor?.let { chuck ->
            builder.addInterceptor(chuck)
        }
        return builder.build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl("http://apicloud.mob.com/")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build()
    }

}