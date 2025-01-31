package com.example.midtest.net

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object KnowNetWork {
    private val latestService = ServiceCreator.create<LatestService>()
    private val beforeService = ServiceCreator.create<BeforeService>()
    private val messageService= ServiceCreator.create<MessageService>()
    suspend fun latestNew() = latestService.latestNew().await()
    suspend fun beforeNew(date: String) = beforeService.getBefore(date).await()

    suspend fun getMessage(data :String)= messageService.getMessage(data).await()
    //使用协程简化回调
    private suspend fun <T> Call<T>.await(): T {
        //suspendCoroutine函数必须在协程作用域或挂起函数中才能调用,主要作用是将当前协程立即挂起,然后在一个普通线程中执行Lambda表达式中代码.
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    //让协程恢复
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}