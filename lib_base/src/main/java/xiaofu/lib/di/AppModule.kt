package xiaofu.lib.di

import com.readystatesoftware.chuck.ChuckInterceptor
import cqebd.student.di.EbdViewModelModule
import cqebd.student.network.EbdVideoService
import cqebd.student.network.EbdWorkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xiaofu.lib.BaseApp
import xiaofu.lib.base.http.LiveDataCallAdapterFactory
import xiaofu.lib.base.http.StringConverterFactory
import xiaofu.lib.base.http.gateway.GatewayInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2018/12/19.
 */
@Module(includes = [EbdViewModelModule::class])
class AppModule {

    @Provides
    fun provideApplication(): BaseApp {
        return BaseApp.instance
    }

    @Singleton
    @Provides
    fun provideEbdWorkService(app: BaseApp): EbdWorkService {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(ChuckInterceptor(app))
                .addInterceptor(GatewayInterceptor("23393048", "d0c983467d8ced6568e844c0b0a233ae"))
                .build()
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://service.ex.cqebd.cn/")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build()
                .create(EbdWorkService::class.java)
    }

    @Singleton
    @Provides
    fun provideEbdVideoService(app: BaseApp): EbdVideoService {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(ChuckInterceptor(app))
                .addInterceptor(GatewayInterceptor("23776862", "b5ffc0cc02a74953ea9091338117feda"))
                .build()
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://service.onlin.cqebd.cn/")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build()
                .create(EbdVideoService::class.java)
    }

}