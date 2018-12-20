package xiaofu.lib.di

import com.readystatesoftware.chuck.ChuckInterceptor
import cqebd.student.di.EbdViewModelModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xiaofu.lib.BaseApp
import xiaofu.lib.base.http.LiveDataCallAdapterFactory
import xiaofu.lib.base.http.StringConverterFactory
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
    fun provideOkHttpClient(app: BaseApp): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(ChuckInterceptor(app))
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://apicloud.mob.com/")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build()
    }

}