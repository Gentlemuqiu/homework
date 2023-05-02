package com.example.midtest

import androidx.lifecycle.liveData
import com.example.midtest.net.KnowNetWork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    fun getMessage(data :String)= fire(Dispatchers.IO){
        val messageResponse=KnowNetWork.getMessage(data)
        run{
            val message=messageResponse
            Result.success(message)
        }
    }

    fun latestNew() = fire(Dispatchers.IO){
        val latestResponse = KnowNetWork.latestNew()
        run {
            val latest = latestResponse
            Result.success(latest)
        }
    }

    fun beforeNew(date: String)= fire(Dispatchers.IO){
        val beforeResponse =KnowNetWork.beforeNew(date)
        run {
            val before = beforeResponse
            Result.success(before)
        }
    }



     fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}