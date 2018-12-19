package xiaofu.lib.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import xiaofu.lib.BaseApp
import javax.inject.Singleton

/**
 *
 * Created by @author xiaofu on 2018/12/19.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: BaseApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}