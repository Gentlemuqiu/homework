package com.example.midtest

import androidx.lifecycle.liveData
import com.example.midtest.net.KnowNetWork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    fun latestNew() = fire(Dispatchers.IO){
        val latestResponse = KnowNetWork.latestNew()
        run {
            val latest = latestResponse
            Result.success(latest)
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}