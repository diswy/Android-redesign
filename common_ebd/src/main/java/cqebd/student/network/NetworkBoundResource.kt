package cqebd.student.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor() {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)// 初始化空数据，开始loading
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()// 从本地数据库查询数据
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)// 移除一个观察者
            if (shouldFetch(data)) {// 判断是否请求网络
                fetchFromNetwork(dbSource)// 发起网络请求
            } else {
                result.addSource(dbSource) { newData ->
                    // 把本地数据给观察者，提示加载成功
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            // 理论上这里应该对ApiResponse进行封装判断
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            response?.let { requestType ->
                Observable.just(requestType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            saveCallResult(requestType)
                            result.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }, {
                            setValue(Resource.error(it.message ?: "unknown error", null))
                        })
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<RequestType>

}