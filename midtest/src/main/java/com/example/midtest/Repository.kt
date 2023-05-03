package com.example.midtest

import androidx.lifecycle.liveData
import com.example.midtest.net.KnowNetWork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {

    fun getMessage(data :String)= fire(Dispatchers.IO){
        val messageResponse=KnowNetWork.getMessage(data)
        run{
            Result.success(messageResponse)
        }
    }

    fun latestNew() = fire(Dispatchers.IO){
        val latestResponse = KnowNetWork.latestNew()
        run {
            Result.success(latestResponse)
        }
    }

    fun beforeNew(date: String)= fire(Dispatchers.IO){
        val beforeResponse =KnowNetWork.beforeNew(date)
        run {
            Result.success(beforeResponse)
        }
    }



     private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

}