package cqebd.student.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cqebd.student.viewmodel.EbdViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import xiaofu.lib.network.di.ViewModelFactory
import xiaofu.lib.network.di.ViewModelKey

/**
 *
 * Created by @author xiaofu on 2018/12/20.
 */
@Suppress("unused")
@Module
abstract class EbdViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EbdViewModel::class)
    abstract fun bindUserViewModel(userViewModel: EbdViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}